package servlet;

import bean.LoginFormBean;
import bean.User;
import org.apache.log4j.Logger;
import repository.UserRepository;
import service.LoginValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static constants.Constants.*;

public class LoginServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(LoginServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(LOGIN_PAGE);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginFormBean bean = parseLoginForm(req);
        Map<String, String> errors = new HashMap<>();

        errors.putAll(new LoginValidator(bean).validate());
        if (errors.isEmpty()) {
            getServletContext().getRequestDispatcher(LOGININFO_JSP).forward(req, resp);
        } else {
            unsuccessfulExecution(bean, errors, req, resp);
        }
    }

    private void unsuccessfulExecution(LoginFormBean bean, Map<String, String> errors,
                                       HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        bean.setPassword(EMPTY_STRING);
        req.setAttribute(BEAN, bean);
        req.setAttribute(ERRORS, errors);
        req.getRequestDispatcher(LOGIN_PAGE).forward(req, resp);
    }

    private LoginFormBean parseLoginForm(HttpServletRequest request) {
        LoginFormBean bean = new LoginFormBean();
        bean.setLogin(request.getParameter(LOGIN));
        bean.setPassword(request.getParameter(PASSWORD_1));
        return bean;
    }
}
