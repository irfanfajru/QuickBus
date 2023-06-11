package com.quickbus.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name="bus")
public class Bus implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", insertable = false, updatable = false, nullable = false)
    private UUID id;

    @Column(name="name",nullable = false)
    private String name;

    @Column(name="type",nullable = false)
    private String type;

    @Column(name="capacity",nullable = false)
    private int capacity;

    @Column(name="seat_format", nullable = false)
    private String seatFormat;

    @Column(name="ac")
    private boolean ac = false;

    @Column(name="entertainment")
    private boolean entertainment = false;

    @Column(name="recliner_seat")
    private boolean reclinerSeat = false;

    @Column(name = "smooking_room")
    private boolean smookingRoom = false;

    @Column(name = "rest_room")
    private boolean restRoom = false;

    @Column(name = "luggage")
    private boolean luggage = false;

    @Column(name = "big_luggage")
    private boolean bigLuggage = false;

    @Column(name = "toilet")
    private boolean toilet = false;

    @Column(name = "created_at",updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;

    @Column(name = "deleted_at")
    private Date deletedAt;

}
