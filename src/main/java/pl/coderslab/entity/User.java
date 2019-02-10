package pl.coderslab.entity;


import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import pl.coderslab.validator.AdminValidationUserGroup;
import pl.coderslab.validator.FullValidationUserGroup;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(groups = {Default.class, FullValidationUserGroup.class, AdminValidationUserGroup.class}, message = "Login nie może być pusty!")
    @Column(unique = true)
    private String login;

    @Size(min = 6, groups = {Default.class, FullValidationUserGroup.class} , message = "Hasło musi mieć minimum 6 znaków")
    private String password;

    @Transient
    private String repeatedPassword;

    @NotBlank(groups = {FullValidationUserGroup.class , AdminValidationUserGroup.class} , message = "Email nie może być pusty!")
    @Email(groups = {FullValidationUserGroup.class , AdminValidationUserGroup.class} , message = "Niepoprawny Email!")
    @Column(unique = true)
    private String email;

    private boolean isAdmin = false;

    @ManyToMany(mappedBy = "users",fetch = FetchType.EAGER)
    private List<Phone> phones;

    public User() {
    }

    public void deleteFavoriteSmartphone(Phone phone){
        this.phones.remove(phone);
    }

    public void addFavoriteSmartphone(Phone phone){
        this.phones.add(phone);
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
    public boolean getIsAdmin() {
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

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }
}
