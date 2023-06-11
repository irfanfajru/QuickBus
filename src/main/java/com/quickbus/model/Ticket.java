package com.quickbus.model;

import com.quickbus.model.oauth.User;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "ticket")
public class Ticket implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(name = "price")
    private Double price;

    @Column(name = "status")
    private String status;

    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;

//    many to one user
    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;

//    many to one travel
    @ManyToOne
    @JoinColumn(name = "travel_id",referencedColumnName = "id")
    private Travel travel;
}
