package constants;

import java.util.regex.Pattern;

public class Constants {
    public static final Pattern LOGIN_PATTERN = Pattern.compile("^[a-zA-Z]{2,16}$");
    public static final Pattern PASSWORD_PATTERN = Pattern.compile("^[a-zA-Z0-9_\\-]{2,18}$");
    public static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z]{2,16}$");
    public static final Pattern EMAIL_PATTERN = Pattern.compile("^([a-zA-Z0-9_\\.\\-]+)@([\\da-zA-Z\\.\\-]+)\\.([a-zA-Z\\.]{2,6})$");
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String LOGIN = "login";
    public static final String PASSWORD_1 = "password";
    public static final String PASSWORD_2 = "confirmpassword";
    public static final String PASSWORDS = "passwords";
    public static final String EMAIL = "email";
    public static final String GENDER = "gender";
    public static final String EMPTY_STRING = "";
    public static final String BEAN = "bean";
    public static final String ERRORS = "errors";

    public static final String LOGIN_PAGE = "/login.jsp";
    public static final String LOGININFO_JSP =  "/loginInfo.jsp";
    public static final String REGISTRATION_JSP = "/registration.jsp";
    public static final String USERINFO_JSP = "/UserInfo.jsp";
}
