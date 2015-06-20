package net.aditsu.phonebook;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class StartPhonebook {
	public static void main(final String... args) throws Exception {
		Server server = new Server(8080);
		new WebAppContext(server, "webapp", "");
		server.start();
	}
}
