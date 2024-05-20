package edu.comillas.icai.gitt.pat.spring.p5.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Token {
    @Id @GeneratedValue(strategy = GenerationType.UUID) public String id;
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToOne @JoinColumn(name = "usuario_id",referencedColumnName = "id") AppUser appUser;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }



}
