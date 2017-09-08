package bean;

public class User {
    private String firstName;
    private String lastName;
    private String password;
    private String password2;
    private String mail;
    private String login;
    private String gender;

    void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    void setMail(String mail) {
        this.mail = mail;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    void setPassword2(String password2) {
        this.password2 = password2;
    }
}
