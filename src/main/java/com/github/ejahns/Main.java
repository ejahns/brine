package com.github.ejahns;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.github.ejahns.model.Feature;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {

	@Parameter(names = {"-h", "--help"}, description = "Display help", help = true)
	private boolean help;

	@Parameter(names = {"-l", "--log"}, description = "Log and report errors rather than throwing exceptions")
	private boolean logErrors;

	@Parameter(names = {"-p", "--pretty"}, description = "Pretty print json")
	private boolean pretty;

	@Parameter(names = {"-s", "--suppress"}, description = "Suppress json output, only report feature validity")
	private boolean suppress;

	@Parameter(description = "files")
	private List<String> files;

	public static void main(String[] args) throws URISyntaxException, FileNotFoundException, MalformedURLException {
		Main main = new Main();
		JCommander commander = JCommander.newBuilder()
			.addObject(main)
			.programName("brine")
			.build();
		commander.parse(args);
		if (args.length == 0 || main.help) {
			commander.usage();
			return;
		}
		main.run();
	}

	public void run() throws FileNotFoundException {
		ArrayList<File> fileList = new ArrayList<>();
		for (String s : files) {
			File f = new File(s);
			File[] files = f.listFiles((d, name) -> name.endsWith(".feature"));
			if (files != null) {
				fileList.addAll(Arrays.asList(files));
			}
		}
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
				System.out.println("------------------------------------------------------------------------------------");
				System.out.println(f.toString());
				System.out.println("------------------------------------------------------------------------------------");
				List<String> errors = new ArrayList<>();
				Feature cure = PickleJar.cureCollectErrors(f, errors);
				if (errors.size() > 0) {
					System.out.println("\terrors:");
					for (String s : errors) {
						System.out.println("\t\t" + s);
					}
					if (!suppress) {
						System.out.println(gson.toJson(cure));
					}
				}
				else {
					if (!suppress) {
						System.out.println(gson.toJson(cure));
					}
					else {
						System.out.println("\tvalid");
					}
				}
				System.out.println();
			}
		}
	}
}
