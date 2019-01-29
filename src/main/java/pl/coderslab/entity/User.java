package pl.coderslab.entity;


import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import pl.coderslab.validator.FullValidationUserGroup;

import javax.persistence.*;
import javax.validation.groups.Default;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(groups = {Default.class, FullValidationUserGroup.class})
    @Column(unique = true)
    private String login;

    @NotBlank(groups = {Default.class, FullValidationUserGroup.class})
    private String password;

    @Transient
    private String repeatedPassword;

    @NotBlank(groups = FullValidationUserGroup.class)
    @Email(groups = FullValidationUserGroup.class)
    @Column(unique = true)
    private String email;

    private boolean isAdmin = false;


    public User() {
    }


    @Override
    public String toString() {
        return this.login;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getRepeatedPassword() {
        return repeatedPassword;
    }

    public void setRepeatedPassword(String repeatedPassword) {
        this.repeatedPassword = repeatedPassword;
    }

}
