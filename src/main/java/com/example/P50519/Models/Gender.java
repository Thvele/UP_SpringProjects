package com.example.P50519.Models;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Gender {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idgender;

    private String gendername;

    @OneToMany(mappedBy = "idgender", fetch = FetchType.EAGER)
    private Collection<UserData> userData;

    public Gender() {
    }

    public Gender(String gendername, Collection<UserData> userData) {
        this.gendername = gendername;
        this.userData = userData;
    }

    public Long getIdgender() {
        return idgender;
    }

    public void setIdgender(Long idgender) {
        this.idgender = idgender;
    }

    public String getGendername() {
        return gendername;
    }

    public void setGendername(String gendername) {
        this.gendername = gendername;
    }

    public Collection<UserData> getUserData() {
        return userData;
    }

    public void setUserData(Collection<UserData> userData) {
        this.userData = userData;
    }
}
