package com.github.ejahns;

import java.util.ArrayDeque;
import java.util.Deque;

import com.github.ejahns.model.GherkinDocument;
import com.github.ejahns.model.GherkinElement;

public class GherkinElementStack {

	private Deque<GherkinElement> stack = new ArrayDeque<>();
	private GherkinDocument result = null;

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
			if (!(pop instanceof GherkinDocument)) {
				throw new IllegalStateException();
			}
			result = (GherkinDocument) pop;
		}
	}

	public GherkinDocument resolve() {
		return result;
	}

	public void consume(Token t) {
		stack.peek().consume(t);
	}
}
