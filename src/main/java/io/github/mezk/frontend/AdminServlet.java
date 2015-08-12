package io.github.mezk.frontend;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.github.mezk.templater.PageGenerator;

public class AdminServlet extends HttpServlet {

    public final static String ADMIN_PAGE_URL = "/admin";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println(PageGenerator.getPage(ADMIN_PAGE_URL + ".html", null));
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(HttpServletResponse.SC_OK);
        String timeString = req.getParameter("timeToShutdown");
        if(timeString != null && !timeString.isEmpty()) {
            int timeToShutdown = Integer.valueOf(timeString);
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
