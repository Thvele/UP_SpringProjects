package com.example.P50519.Models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iduser;

    @NotBlank(message = "Вы не указали логин!")
    @Size(min = 4, max = 20, message = "Логин должен содержать от 4 до 20 символов")
    private String login;

    @NotBlank(message = "Вы не указали пароль!")
    @Size(min = 8, message = "Пароль должен содержать от 8 символов")
    private String password;

    private Boolean active;

    private String profilePic;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userData_id")
    private UserData userData;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @ManyToMany
    @JoinTable (name = "favorite",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "car_id"))
    private List<Car> cars;

    public User() {
    }

    public User(String login, String password, Boolean active, String profilePic, UserData userData, Set<Role> roles, List<Car> cars) {
        this.login = login;
        this.password = password;
        this.active = active;
        this.profilePic = profilePic;
        this.userData = userData;
        this.roles = roles;
        this.cars = cars;
    }

    public Long getIduser() {
        return iduser;
    }

    public void setIduser(Long iduser) {
        this.iduser = iduser;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}
