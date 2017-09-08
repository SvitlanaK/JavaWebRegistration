package servlet;

import bean.RegistrationFormBean;
import bean.User;
import org.apache.commons.collections4.MapUtils;
import org.apache.log4j.Logger;
import repository.UserRepository;
import service.RegistrationValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static constants.Constants.*;

public class RegistrationServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(RegistrationServlet.class);

    Map<String, String> errors = new HashMap<>();

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RegistrationFormBean bean = parseRegistrationForm(req);
            errors.clear();
            errors.putAll(validateRequest(bean));
            if (MapUtils.isNotEmpty(errors)) {
                unsuccessfulExecution(bean, errors, req, resp);
            } else {
                processRegistration(req, resp, bean);
            }
    }

    private Map<String, String> validateRequest(RegistrationFormBean bean)
            throws IOException, ServletException {
        errors.putAll(new RegistrationValidator(bean).validate());
        return Collections.unmodifiableMap(errors);
    }

    private void processRegistration(HttpServletRequest req, HttpServletResponse resp, RegistrationFormBean bean)
            throws IOException, ServletException {
        User user = bean.toUser();
        UserRepository.add(user);
        getServletContext().getRequestDispatcher(USERINFO_JSP).forward(req, resp);
    }

    private void unsuccessfulExecution(RegistrationFormBean bean, Map<String, String> errors,
                                       HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        bean.setPassword(EMPTY_STRING);
        bean.setPassword2(EMPTY_STRING);
        req.setAttribute(BEAN, bean);
        req.setAttribute(ERRORS, errors);
        req.getRequestDispatcher(REGISTRATION_JSP).forward(req, resp);
    }

    private RegistrationFormBean parseRegistrationForm(HttpServletRequest request) {
        RegistrationFormBean bean = new RegistrationFormBean();
        bean.setFirstName(request.getParameter(FIRST_NAME));
        bean.setLastName(request.getParameter(LAST_NAME));
        bean.setLogin(request.getParameter(LOGIN));
        bean.setPassword(request.getParameter(PASSWORD_1));
        bean.setPassword2(request.getParameter(PASSWORD_2));
        bean.setMail(request.getParameter(EMAIL));
        bean.setGender(request.getParameter(GENDER));
        return bean;
    }
}
