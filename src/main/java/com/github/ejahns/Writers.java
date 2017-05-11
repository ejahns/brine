package com.github.ejahns;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;

class Writers {

	static final PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out), true);
	static final PrintWriter err = new PrintWriter(new OutputStreamWriter(System.err), true);
}
