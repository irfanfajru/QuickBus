package com.investree.demo.model;

import com.investree.demo.model.oauth.User;
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

    @Column(name="nama",nullable = false)
    private String nama;

    @Column(name="alamat",nullable = false,columnDefinition = "TEXT")
    private String alamat;

//    one to one user
    @OneToOne
    @JoinColumn(name="id_user",referencedColumnName = "id")
    private User user;
}
