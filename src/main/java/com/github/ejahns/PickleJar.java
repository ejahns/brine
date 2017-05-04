package com.github.ejahns;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.net.URI;

import com.github.ejahns.model.Feature;

public class PickleJar {

	public static Feature ferment(Reader reader) {
		TokenQueue tokenQueue = new TokenQueue(reader);
		return new Parser().parse(tokenQueue);
	}

	public static Feature ferment(File file) throws FileNotFoundException {
		Feature ferment = ferment(new FileReader(file));
		ferment.setUri(file.toURI());
		return ferment;
	}

	public static Feature ferment(URI uri) throws FileNotFoundException {
		return ferment(new File(uri));
	}
}
