package com.example.P50519.Models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private Boolean active;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER) // Указываем класс на котором оснывывается атрибут
    @CollectionTable(name = "user_roles",
                     joinColumns = @JoinColumn(name = "user_id")) // Создаём талицу с ролями для пользователей
    @Enumerated(EnumType.STRING) // Указываем что это перечисленние
    private Set<Role> roles;

    @NotBlank(message = "Поле не должно быть пустым!")
    @Size(min = 1, max = 150, message = "Поле должно содержать от 1 до 150 символов")
    private String surname;
    @NotBlank(message = "Поле не должно быть пустым!")
    @Size(min = 1, max = 150, message = "Поле должно содержать от 1 до 150 символов")
    private String name;

    private String middleName;
    @NotNull(message = "Поле не моет быть пустым")
    @Pattern(regexp = "[0-9][0-9][0-9][0-9] [0-9][0-9][0-9][0-9][0-9][0-9]", message = "Паспорт не соответсвует маске ввода")
    private String passport;
    @NotNull(message = "Поле не может быть пустым")
    @Pattern(regexp = "[0-9][0-9][0-9][0-9]-[0-1][0-9]-[0-3][0-9]", message = "Дата не соотвествует маске ввода")
    private String birthday;


    public Employee() {
    }

    public Employee(String username, String password, Boolean active, Set<Role> roles, String surname, String name, String middleName, String passport, String birthday) {
        this.username = username;
        this.password = password;
        this.active = active;
        this.roles = roles;
        this.surname = surname;
        this.name = name;
        this.middleName = middleName;
        this.passport = passport;
        this.birthday = birthday;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
