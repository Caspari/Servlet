package de.unimarburg.eise12.servlet;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Server {
	private Map<String, IServlet> routes;
	
	public Server() {
		this(new HashMap<String, IServlet>());
	}
	public Server(Map<String, IServlet> routes) {
		System.out.println("Starting the server!");
		this.routes = routes;
	}
	
	public Server register(String route, IServlet servlet) {
		routes.put(route, servlet);
		System.out.println("Registering servlet '" + servlet.toString() + "' for route '" + route + "'");
		return this;
	}
	
	/**
	 * Selects an appropriate servlet depending on the given url. Then dispatches the request 
	 * to the servlet.
	 * @param url
	 */
	public void get(String url) {		
		try {
			URI requestUrl = URI.create(url);
			Map<String, List<String>> params = prepareParameters(requestUrl.getQuery());
			String path = requestUrl.getPath();
			
			if(!routes.containsKey(path)) {
				System.out.println("No service found for path '" + path + "'!!!");
				return;
			}
			
			IServlet servlet = routes.get(path);
			
			PrintWriter output = new PrintWriter(System.out);
			
			System.out.println("Dispatching path '" + path + "'");
			servlet.process(params, output);
			output.flush();
			
		} catch (UnsupportedEncodingException e) {
			System.out.println("Encoding of your querystring is not supported. Expected UTF-8");
		}	
	}
	
	/**
	 * Extracts the map of parameters from the given query string
	 * Adopted from {@link http://stackoverflow.com/questions/1667278/parsing-query-strings-in-java} 
	 * 
	 * @param query
	 * @return the map of parameters
	 * @throws UnsupportedEncodingException
	 */
	private Map<String, List<String>> prepareParameters(String query) throws UnsupportedEncodingException {
		Map<String, List<String>> params = new HashMap<String, List<String>>();
		
		if(query == null) 
			return params;
		
		for (String param : query.split("&")) {
            String pair[] = param.split("=");
            String key = URLDecoder.decode(pair[0], "UTF-8");
            String value = "";
            if (pair.length > 1) {
                value = URLDecoder.decode(pair[1], "UTF-8");
            }
            List<String> values = params.get(key);
            if (values == null) {
                values = new ArrayList<String>();
                params.put(key, values);
            }
            values.add(value);
        }
		return params;		
	}

	
}
