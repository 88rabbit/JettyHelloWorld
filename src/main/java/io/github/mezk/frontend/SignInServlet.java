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
 * Sign in servlet.
 * @author <a href="mailto:andreyselkin@gmail.com">Andrei Selkin</a>
 */
public class SignInServlet extends HttpServlet {

    /**Sign in page URL.*/
    public static final String SIGNIN_PAGE_URL = "/signin";
    /**Time to auithorization responce.*/
    public static final int TIME_TO_AUTH_RESPONSE = 100;

    /**Account service.*/
    private AccountService accountService;

    /**
     * Class constructor.
     * @param accountService account service.
     */
    public SignInServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        resp.getWriter().println(PageGenerator.getPage(SIGNIN_PAGE_URL + ".html", null));
        resp.setStatus(HttpServletResponse.SC_OK);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        final String name = req.getParameter("name");
        final String password = req.getParameter("password");

        final Map<String, Object> pageVariables = new HashMap<>();
        final UserProfile profile = accountService.getUser(name);
        if (profile != null && profile.getPassword().equals(password)) {
            pageVariables.put("loginStatus", "Login passed");
            resp.getWriter().println(PageGenerator.getPage("authstatus.html", pageVariables));
            try {
                final String email = profile.getEmail();
                pageVariables.put("email", email);
                pageVariables.put("password", password);
                Thread.sleep(TIME_TO_AUTH_RESPONSE);
                resp.getWriter().println(PageGenerator.getPage("authresponse.txt", pageVariables));
            }
            catch (InterruptedException e) {
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
