package com.example.P50519.Models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "user_data")
public class UserData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iddata;

    @NotBlank(message = "ФИО не может быть пустым")
    private String FIO;

    @NotBlank(message = "Почтовый адрес не может быть пустым!")
    private String email;

    private String phone;

    @OneToOne(mappedBy = "userData")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    private Gender idgender;


    public UserData() {
    }

    public UserData(String FIO, String email, String phone, User user, Gender idgender) {
        this.FIO = FIO;
        this.email = email;
        this.phone = phone;
        this.user = user;
        this.idgender = idgender;
    }

    public Long getIddata() {
        return iddata;
    }

    public void setIddata(Long iddata) {
        this.iddata = iddata;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Gender getIdgender() {
        return idgender;
    }

    public void setIdgender(Gender idgender) {
        this.idgender = idgender;
    }
}
