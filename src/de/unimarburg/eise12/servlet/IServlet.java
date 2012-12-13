package de.unimarburg.eise12.servlet;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public interface IServlet {
	void process(Map<String, List<String>> params, PrintWriter output);
}