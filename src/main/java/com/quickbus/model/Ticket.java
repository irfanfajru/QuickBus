package com.quickbus.model;

import com.quickbus.model.oauth.User;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "ticket")
public class Ticket implements Serializable {
    @Id
    @Column(name = "id",insertable = false,updatable = false,nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Positive
    @Column(name = "price",nullable = false)
    private Double price;

    @Positive
    @Column(name = "passenger",nullable = false)
    private int passenger;

    @Column(name = "status")
    private String status = "pending";

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

//    one to many passenger
    @OneToMany(mappedBy = "ticket")
    private List<Passenger> passengerList;
}
