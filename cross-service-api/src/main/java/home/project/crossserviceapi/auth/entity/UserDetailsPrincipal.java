package home.project.crossserviceapi.auth.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UserDetailsPrincipal extends User {
    private static final long serialVersionUID = 6316118676849138004L;
    private String externalData;
    private String firstName;
    private String middleName;
    private String lastName;

    public UserDetailsPrincipal(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public UserDetailsPrincipal(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public UserDetailsPrincipal(String username, String password, Collection<? extends GrantedAuthority> authorities, String externalData, FullName fullName) {
        super(username, password, authorities);
        this.externalData = externalData;
        this.firstName = fullName.getFirstName();
        this.middleName = fullName.getMiddleName();
        this.lastName = fullName.getLastName();
    }

    public UserDetailsPrincipal(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, String externalData, FullName fullName) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.externalData = externalData;
        this.externalData = externalData;
        this.firstName = fullName.getFirstName();
        this.middleName = fullName.getMiddleName();
        this.lastName = fullName.getLastName();
    }

    public String getExternalData() {
        return externalData;
    }

    public void setExternalData(String externalData) {
        this.externalData = externalData;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /*
    @Override
    public String toString() {
        return new StringBuilder(super.toString())
                .append("Full_Name : { ")
                .append("firstName : ").append(firstName).append(",")
                .append("middleName : ").append(middleName).append(",")
                .append("lastName : ").append(lastName).append("}")
                .toString();
    }
    */
}
