package io.github.mezk.frontend;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.github.mezk.templater.PageGenerator;

/**
 * Processes requests to the admin page and generates responses.
 * @author <a href="mailto:andreyselkin@gmail.com">Andrei Selkin</a>
 */
public class AdminServlet extends HttpServlet {

    /**Admin page URL.*/
    public static final String ADMIN_PAGE_URL = "/admin";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        response.getWriter().println(PageGenerator.getPage(ADMIN_PAGE_URL + ".html", null));
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        final String timeString = request.getParameter("timeToShutdown");
        if (timeString != null && !timeString.isEmpty()) {
            final int timeToShutdown = Integer.valueOf(timeString);
            try {
                System.out.println("Server will be down after " + timeToShutdown + " seconds!");
                TimeUnit.SECONDS.sleep(timeToShutdown);
                System.out.println("Shutwodn...");
                System.exit(1);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
