package de.unimarburg.eise12.servlet.tests;

import de.unimarburg.eise12.servlet.Server;

public class Tester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Server server = setupServer();
		server.get("/solution?name1=Max&surname1=Mustermann&name2=Luise&surname2=Musterfrau");
		server.get("/composite?name1=Heinz&surname1=Schneider&name2=Petra&surname2=Meyer");
	}
	
	static Server setupServer() {
		Server server = new Server();
		/*
		server.register("/solution", new CompatiblePartnerServlet());
		server.register("/composite", new CompositeServlet(new CompatiblePartnerServlet(), new CompatiblePartnerServlet()));
		*/
		return server;
	}

}
