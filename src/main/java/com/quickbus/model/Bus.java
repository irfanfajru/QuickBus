package com.quickbus.model;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Table(name="bus")
//@Where(clause = "deleted_at is null")
public class Bus extends AbstractDate implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", insertable = false, updatable = false, nullable = false)
    private UUID id;

    @NotBlank
    @Column(name="name",nullable = false)
    private String name;

    @NotBlank
    @Column(name="type",nullable = false)
    private String type;

    @Positive
    @Column(name="capacity",nullable = false)
    private int capacity;

    @NotBlank
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
}
