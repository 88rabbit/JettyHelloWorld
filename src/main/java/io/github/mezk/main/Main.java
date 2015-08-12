package io.github.mezk.main;

import javax.servlet.Servlet;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import io.github.mezk.frontend.AdminServlet;
import io.github.mezk.frontend.SignInServlet;
import io.github.mezk.frontend.SignUpServlet;

/**
 * @author Andrei Selkin
 */
public class Main {
    public static void main(String[] args) throws Exception {
        int port = 8080;
        if (args.length == 1) {
            String portString = args[0];
            port = Integer.valueOf(portString);
        }

        System.out.append("Starting Jetty at port: ").append(String.valueOf(port)).append('\n');

        AccountService accountService = new AccountService();

        Servlet signin = new SignInServlet(accountService);
        Servlet signUp = new SignUpServlet(accountService);
        Servlet admin = new AdminServlet();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(signin), SignInServlet.SIGNIN_PAGE_URL);
        context.addServlet(new ServletHolder(signUp), SignUpServlet.SIGNUP_PAGE_URL);
        context.addServlet(new ServletHolder(admin), AdminServlet.ADMIN_PAGE_URL);

        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setDirectoriesListed(true);
        resource_handler.setResourceBase("static");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{ resource_handler, context });

        Server server = new Server(port);
        server.setHandler(handlers);

        server.start();
        server.join();
    }
}
