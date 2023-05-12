package com.example.demotwo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String name;

    private String surname;

    private String email;

    private String password;

    public void copyValues(final User fromUser) {
        name = fromUser.name;
        surname = fromUser.surname;
        password = fromUser.password;
        email = fromUser.email;
    }

}
