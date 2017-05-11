package com.github.ejahns.token;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

public class TokenProvider {

	private final Deque<Token> tokenDeque = new ArrayDeque<>();
	private final Tokenizer tokenizer = new Tokenizer();
	private final LineNumberReader reader;

	private List<String> errors;
	private boolean collectErrors = false;

	public TokenProvider(Reader reader) {
		this.reader = new LineNumberReader(reader);
	}

	public TokenProvider(Reader reader, List<String> errors) {
		this.collectErrors = true;
		this.errors = errors;
		this.reader = new LineNumberReader(reader);
	}

	public Token next() {
		if (!tokenDeque.isEmpty()) {
			return tokenDeque.pop();
		}
		return readNextToken();
	}

	public void addToFront(Deque<Token> queue) {
		Iterator<Token> tokenIterator = queue.descendingIterator();
		while (tokenIterator.hasNext()) {
			tokenDeque.addFirst(tokenIterator.next());
		}
	}

	private Token readNextToken() {
		String line;
		try {
			line = reader.readLine();
			if (null != line) {
				line = line.replaceAll("\\p{C}", "");
			}
			Token token = tokenizer.tokenize(line, reader.getLineNumber());
			if (null != token) {
				return token;
			}
			else {
				return readNextToken();
			}
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
		catch (TokenizerException e) {
			if (collectErrors) {
				errors.add(e.toString());
				return readNextToken();
			}
			throw e;
		}
	}
}
