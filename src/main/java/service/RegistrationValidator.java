package service;

import bean.RegistrationFormBean;
import repository.UserRepository;

import java.util.Collections;
import java.util.Map;

import static constants.Constants.*;

public class RegistrationValidator extends AbstractBeanValidator {

    private final RegistrationFormBean bean;

    public RegistrationValidator(RegistrationFormBean bean) {
        this.bean = bean;
    }

    @Override
    public Map<String, String> validate() {
        simpleValidation(bean.getFirstName(), NAME_PATTERN,
                FIRST_NAME, "Please enter a valid first name");
        simpleValidation(bean.getLastName(), NAME_PATTERN,
                LAST_NAME, "Please enter a valid last name");
        simpleValidation(bean.getLogin(), LOGIN_PATTERN,
                LOGIN, "Please enter a valid login");
        simpleValidation(bean.getPassword(), PASSWORD_PATTERN,
                PASSWORD_1, "Please enter a valid password1");
        simpleValidation(bean.getPassword2(), PASSWORD_PATTERN,
                PASSWORD_2, "Please enter a valid password2");
        simpleValidation(bean.getMail(), EMAIL_PATTERN,
                EMAIL, "Please enter a valid email");
        simpleValidation(bean.getLogin(), NAME_PATTERN,
                LOGIN, "Please enter a valid login");

        validatePasswords(bean.getPassword(), bean.getPassword2());
        for (String key : UserRepository.getUsers().keySet()) {
            validateLogin(bean.getLogin(), key);
        }
        return Collections.unmodifiableMap(errors);
    }

    private void validatePasswords(String pwd1, String pwd2) {
        if (pwd1 == null || pwd2 == null) {
            errors.put(PASSWORDS, "Passwords cannot be nulls");
        } else if (!pwd1.equals(pwd2)) {
            errors.put(PASSWORDS, "Password1 and password2 are not equal");
        }
    }

    private void validateLogin(String login, String loginMap) {
        if (login.equals(loginMap)) {
            errors.put("login", "login already exist");
        }
    }
}
