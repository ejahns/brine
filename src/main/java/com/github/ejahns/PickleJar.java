package com.github.ejahns;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.net.URI;

import com.github.ejahns.model.Feature;

public class PickleJar {

	public static Feature cure(Reader reader) {
		TokenQueue tokenQueue = new TokenQueue(reader);
		return new Parser().parse(tokenQueue);
	}

	public static Feature cure(File file) throws FileNotFoundException {
		Feature ferment = cure(new FileReader(file));
		ferment.setUri(file.toURI());
		return ferment;
	}

	public static Feature cure(URI uri) throws FileNotFoundException {
		return cure(new File(uri));
	}
}
