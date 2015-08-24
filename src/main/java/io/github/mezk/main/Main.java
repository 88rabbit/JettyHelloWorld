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
 * Main class.
 * @author <a href="mailto:andreyselkin@gmail.com">Andrei Selkin</a>
 */
public final class Main {

    /**Server default port.*/
    public static final int DEFAULT_PORT = 8080;

    /**Default constructor.*/
    private Main() { }

    /**
     * Main method.
     * @param args command line arguments.
     * @throws Exception if an exception occures during starting or joining the server.
     */
    public static void main(String... args) throws Exception {
        int port = DEFAULT_PORT;
        if (args.length == 1) {
            final String portString = args[0];
            port = Integer.valueOf(portString);
        }

        System.out.append("Starting Jetty at port: ").append(String.valueOf(port)).append('\n');

        final AccountService accountService = new AccountService();

        final Servlet signIn = new SignInServlet(accountService);
        final Servlet signUp = new SignUpServlet(accountService);
        final Servlet admin = new AdminServlet();

        final ServletContextHandler context =
            new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(signIn), SignInServlet.SIGNIN_PAGE_URL);
        context.addServlet(new ServletHolder(signUp), SignUpServlet.SIGNUP_PAGE_URL);
        context.addServlet(new ServletHolder(admin), AdminServlet.ADMIN_PAGE_URL);

        final ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setDirectoriesListed(true);
        resourceHandler.setResourceBase("static");

        final HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resourceHandler, context});

        final Server server = new Server(port);
        server.setHandler(handlers);

        server.start();
        server.join();
    }
}
