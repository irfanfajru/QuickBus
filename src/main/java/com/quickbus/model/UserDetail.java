package com.quickbus.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.quickbus.model.oauth.User;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
@Data
@Entity
@Table(name="user_detail")
public class UserDetail implements Serializable {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="name",nullable = false)
    private String name;

    @Column(name="address",nullable = false,columnDefinition = "TEXT")
    private String address;

    @Column(name = "phone",nullable = false)
    private String phone;
//    one to one user
    @JsonIgnore
    @OneToOne
    @JoinColumn(name="user_id",referencedColumnName = "id")
    private User user;
}
