package io.github.mezk.servlets;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import io.github.mezk.frontend.Frontend;

/**
 * @author Andrei Selkin
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Frontend frontend = new Frontend();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(frontend), "/authform");

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
        server.join();
    }
}