package de.unimarburg.eise12.servlet;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import de.unimarburg.eise12.servlet.IServlet;

public class CompositeServlet implements IServlet {

	IServlet servlet1;
	IServlet servlet2;
	
	public CompositeServlet(IServlet servlet1, IServlet servlet2){
		this.servlet1 = servlet1;
		this.servlet2 = servlet2;
	}
	
	public void process(Map<String, List<String>> params, PrintWriter output) {
		servlet1.process(params, output);
		servlet2.process(params, output);
	}

}
