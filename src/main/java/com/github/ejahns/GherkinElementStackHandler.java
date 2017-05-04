package com.github.ejahns;

import java.util.ArrayDeque;
import java.util.Deque;

import com.github.ejahns.model.Feature;
import com.github.ejahns.model.GherkinElement;

public class GherkinElementStackHandler {

	private Deque<GherkinElement> stack = new ArrayDeque<>();
	private Feature result = null;

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
			stack.peek().add(pop);
		}
		else {
			if (!(pop instanceof Feature)) {
				throw new IllegalStateException();
			}
			result = (Feature) pop;
		}
	}

	public Feature resolve() {
		return result;
	}

	public void consume(Token t) {
		stack.peek().consume(t);
	}
}
