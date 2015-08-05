package io.github.mezk.frontend;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.github.mezk.main.AccountService;
import io.github.mezk.main.UserProfile;
import io.github.mezk.templater.PageGenerator;

public class SignInServlet extends HttpServlet {

    private AccountService accountService;

    public SignInServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        resp.getWriter().println(PageGenerator.getPage("signin.html", null));
        resp.setStatus(HttpServletResponse.SC_OK);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        String name = req.getParameter("name");
        String password = req.getParameter("password");

        Map<String, Object> pageVariables = new HashMap<>();
        UserProfile profile = accountService.getUser(name);
        if (profile != null && profile.getPassword().equals(password)) {
            pageVariables.put("loginStatus", "Login passed");
            resp.getWriter().println(PageGenerator.getPage("authstatus.html", pageVariables));
            try {
                String email = profile.getEmail();
                pageVariables.put("email", email);
                pageVariables.put("password", password);
                Thread.sleep(100);
                resp.getWriter().println(PageGenerator.getPage("authresponse.txt", pageVariables));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else {
            pageVariables.put("loginStatus", "Wrong login/password");
            resp.getWriter().println(PageGenerator.getPage("authstatus.html", pageVariables));
        }

        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
