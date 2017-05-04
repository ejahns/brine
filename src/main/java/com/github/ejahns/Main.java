package com.github.ejahns;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import com.github.ejahns.model.Feature;
import com.google.gson.Gson;

public class Main {

	public static void main(String[] args) throws URISyntaxException, FileNotFoundException, MalformedURLException {
		if (args.length != 1) {
			System.err.print("invalid argument count");
			return;
		}
		File file = new File(args[0]);
		Feature ferment = PickleJar.cure(file);
		String json = new Gson().toJson(ferment);
		System.out.print(json);
		System.out.flush();
	}
}
