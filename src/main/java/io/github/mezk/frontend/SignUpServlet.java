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

/**
 * Sign up servlet.
 * @author <a href="mailto:andreyselkin@gmail.com">Andrei Selkin</a>
 */
public class SignUpServlet extends HttpServlet {

    /**Sign up page URL.*/
    public static final String SIGNUP_PAGE_URL = "/signup";
    /**Account service.*/
    private AccountService accountService;

    /**
     * Class constructor.
     * @param accountService account service.
     */
    public SignUpServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        resp.getWriter().println(PageGenerator.getPage(SIGNUP_PAGE_URL + ".html", null));
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        final String name = req.getParameter("name");
        final String email = req.getParameter("email");
        final String password = req.getParameter("password");

        final Map<String, Object> pageVariables = new HashMap<>();
        if (accountService.addUser(name, new UserProfile(name, password, email))) {
            pageVariables.put("signUpStatus", "New user created");
            resp.getWriter().println(PageGenerator.getPage("signupstatus.html", pageVariables));
        }
        else {
            pageVariables.put("signUpStatus", "User with name: " + name + " already exists");
            resp.getWriter().println(PageGenerator.getPage("signupstatus.html", pageVariables));
        }
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
