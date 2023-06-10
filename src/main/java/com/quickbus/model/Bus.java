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
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(name="name")
    private String name;

    @Column(name="type")
    private String type;

    @Column(name="capacity")
    private int capacity;

    @Column(name="seat_format")
    private String seatFormat;

    @Column(name="ac")
    private boolean ac;

    @Column(name="entertainment")
    private boolean entertainment;

    @Column(name="recliner_seat")
    private boolean reclinerSeat;

    @Column(name = "smooking_room")
    private boolean smookingRoom;

    @Column(name = "rest_room")
    private boolean restRoom;

    @Column(name = "luggage")
    private boolean luggage;

    @Column(name = "big_luggage")
    private boolean bigLuggage;

    @Column(name = "toilet")
    private boolean toilet;

    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;

    @Column(name = "deleted_at")
    private Date deletedAt;

}
