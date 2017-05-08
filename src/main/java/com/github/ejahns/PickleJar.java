package com.github.ejahns;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.github.ejahns.model.Feature;

public class PickleJar {

	public static Feature cure(Reader reader) {
		TokenQueue tokenQueue = new TokenQueue(reader);
		return new Parser().parse(tokenQueue);
	}

	public static Feature cure(File file) throws FileNotFoundException {
		Feature ferment = cure(new FileReader(file));

		//TODO better way to handle location?
		Path activeDir = Paths.get(System.getProperty("user.dir"));
		Path rel = activeDir.relativize(Paths.get(file.toURI()));
		ferment.setRelativeLocation(rel.toString().replaceAll("\\\\", "/"));
		ferment.setAbsoluteLocation(rel.toAbsolutePath().toString().replaceAll("\\\\", "/"));
		return ferment;
	}

	public static Feature cure(URI uri) throws FileNotFoundException {
		return cure(new File(uri));
	}

	public static Feature cureCollectErrors(File file, List<String> errors) throws FileNotFoundException {
		Feature ferment = cureCollectErrors(new FileReader(file), errors);

		//TODO better way to handle location?
		Path activeDir = Paths.get(System.getProperty("user.dir"));
		Path rel = activeDir.relativize(Paths.get(file.toURI()));
		ferment.setRelativeLocation(rel.toString().replaceAll("\\\\", "/"));
		ferment.setAbsoluteLocation(rel.toAbsolutePath().toString().replaceAll("\\\\", "/"));
		return ferment;
	}

	private static Feature cureCollectErrors(FileReader fileReader, List<String> errors) {
		TokenQueue tokenQueue = new TokenQueue(fileReader, errors);
		return (new Parser()).parse(tokenQueue, errors);
	}
}
