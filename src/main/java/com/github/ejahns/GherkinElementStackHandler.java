package com.github.ejahns;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import com.github.ejahns.model.Feature;
import com.github.ejahns.model.interfaces.GherkinElement;

public class GherkinElementStackHandler {

	private List<String> errors;
	private boolean collectErrors = false;

	private Deque<GherkinElement> stack = new ArrayDeque<>();
	private Feature result = null;

	public void setCollectErrors(List<String> errors) {
		this.collectErrors = true;
		this.errors = errors;
	}

	public void push(Class<? extends GherkinElement> clazz) {
		try {
			stack.push(clazz.newInstance());
		}
		catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public void collapse(Class<? extends GherkinElement> clazz) {
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

	public Feature resolve() {
		while (null == result) {
			collapse(GherkinElement.class);
		}
		return result;
	}

	public void consume(Token t) {
		TokenConsumptionHandler.consume(stack.peek(), t);
	}
}
