package home.project.bookingapp.dto;

import home.project.bookingapp.entity.FullName;

public class UserDTO {
    private String login;
    private FullName fullName;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public FullName getFullName() {
        return fullName;
    }

    public void setFullName(FullName fullName) {
        this.fullName = fullName;
    }
}
