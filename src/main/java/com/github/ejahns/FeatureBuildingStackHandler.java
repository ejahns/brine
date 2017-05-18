package com.github.ejahns;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import com.github.ejahns.model.Feature;
import com.github.ejahns.model.interfaces.GherkinElement;
import com.github.ejahns.token.Token;
import com.github.ejahns.token.TokenConsumptionHandler;

class FeatureBuildingStackHandler implements StackHandler<GherkinElement, Token> {

	private final Deque<GherkinElement> stack = new ArrayDeque<>();
	private List<String> errors;
	private boolean collectErrors = false;
	private Feature result = null;

	FeatureBuildingStackHandler() {

	}

	FeatureBuildingStackHandler(List<String> errors) {
		this.collectErrors = true;
		this.errors = errors;
	}

	@Override
	public void push(Class<? extends GherkinElement> clazz) {
		try {
			stack.push(clazz.newInstance());
		}
		catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void popConsume(Class<? extends GherkinElement> clazz) {
		GherkinElement pop = stack.pop();
		if (stack.size() > 0) {
			try {
				GherkinElementConsumptionHandler.consume(stack.peek(), pop);
			}
			catch (ParserException e) {
				if (!collectErrors) {
					throw e;
				}
				errors.add(e.toString());
			}
		}
		else {
			if (!(pop instanceof Feature)) {
				throw new IllegalStateException();
			}
			result = (Feature) pop;
		}
	}

	@Override
	public Feature resolve() {
		while (null == result) {
			popConsume(GherkinElement.class);
		}
		return result;
	}

	@Override
	public void consume(Token t) {
		TokenConsumptionHandler.consume(stack.peek(), t);
	}
}
