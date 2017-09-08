package service;

import bean.LoginFormBean;
import repository.UserRepository;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

import static constants.Constants.*;


public class LoginValidator extends AbstractBeanValidator {


    private final LoginFormBean bean;

    public LoginValidator(LoginFormBean bean) {
        this.bean = bean;
    }

    public Map<String, String> validate() {
        simpleValidation(bean.getLogin(), LOGIN_PATTERN,
                LOGIN, "Please enter a valid login");
        simpleValidation(bean.getPassword(), PASSWORD_PATTERN,
                PASSWORD_1, "Please enter a valid password");
        validateLogIn(bean.getLogin(), bean.getPassword());

        return Collections.unmodifiableMap(errors);
    }

    private void validateLogIn(String login, String password) {
        if (!UserRepository.getUsers().containsKey(login)) {
            errors.put("login", "login is not right");
        } else {
            String pass = UserRepository.getUsers().get(login).getPassword();
            if (pass == null || !Objects.equals(pass, password)) {
                errors.put("password", "Passwords is not right");
            }
        }
    }
}