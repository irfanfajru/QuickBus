package com.quickbus.model;

import com.quickbus.model.oauth.User;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "log")
public class Log implements Serializable {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "activity")
    private String activity;

    @Column(name = "description",columnDefinition = "text")
    private String description;

    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;

//    Many to one user
    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;
}
