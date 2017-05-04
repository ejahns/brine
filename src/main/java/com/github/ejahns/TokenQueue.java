package com.github.ejahns;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class TokenQueue {

	private Deque<Token> tokens = new ArrayDeque<>();

	public Token next() {
		return tokens.pop();
	}

	public void add(Queue<Token> queue) {
		tokens.addAll(queue);
	}
}
