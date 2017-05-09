package com.github.ejahns;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.github.ejahns.model.Feature;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {

	@Parameter(names = {"-l", "--log"}, description = "Logs and reports errors rather than throwing exceptions")
	private boolean logErrors;

	@Parameter(names = {"-p", "--pretty"}, description = "Print pretty json")
	private boolean pretty;

	@Parameter(description = "Feature files to be parsed")
	private List<String> files;

	public static void main(String[] args) throws URISyntaxException, FileNotFoundException, MalformedURLException {
		Main main = new Main();
		JCommander.newBuilder()
			.addObject(main)
			.build()
			.parse(args);
		main.run();
	}

	public void run() throws FileNotFoundException {
		List<File> fileList = files.stream().map(File::new).collect(Collectors.toList());
		Gson gson;
		if (!pretty) {
			gson = new Gson();
		}
		else {
			gson = new GsonBuilder().setPrettyPrinting().create();
		}
		for (File f : fileList) {
			if (!logErrors) {
				System.out.println(f.toString());
				Feature cure = PickleJar.cure(f);
				System.out.println(gson.toJson(cure));
			}
			else {
				System.out.println(f.toString());
				List<String> errors = new ArrayList<>();
				Feature cure = PickleJar.cureCollectErrors(f, errors);
				for (String s : errors) {
					System.out.println(s);
				}
				System.out.println(gson.toJson(cure));
			}
		}
	}
}
