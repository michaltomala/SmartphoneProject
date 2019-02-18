package pl.coderslab.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;
import java.util.List;


@Entity
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nazwa telefonu nie moźe być pusta!")
    @Column(unique = true)
    private String name;

    @Min(value = 100 , message = "Cena telefonu musi być większa od 100zł!")
    private int price;

    @Size(min=20 , message = "Opis musi być większy!")
    private String description;

    @NotNull(message = "Musisz wybrac markę!")
    @ManyToOne(fetch = FetchType.EAGER)
    private Brand brand;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<User> users;

//  todo   przyszłościowo dodać tu też zdjęcie

    public Phone() {
    }

    public void addUser(User user){
        this.users.add(user);
    }

    public void deleteUser(User user){
//        User userToDelete = null;
//        for(User u : this.users){
//            if(u.getId()==user.getId()){
//                userToDelete = u;
//            }
//        }
//        if(userToDelete != null){
//            this.users.remove(userToDelete);
//        }
            this.users.remove(user);
    }

    @Override
    public String toString() {
        return this.name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }



}
