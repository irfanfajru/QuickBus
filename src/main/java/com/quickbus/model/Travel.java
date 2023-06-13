package com.quickbus.model;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
@Entity
@Table(name="travel")
//@Where(clause = "deleted_at is null")
public class Travel extends AbstractDate implements Serializable {
    @Id
    @Column(name = "id",insertable = false,updatable = false,nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "departure",nullable = false)
    private String departure;

    @Column(name = "departure_location",columnDefinition = "text")
    private String departureLocation;

    @Column(name = "departure_date",nullable = false)
    private LocalDate departureDate;

    @Column(name = "departure_time",nullable = false)
    private LocalTime departureTime;

    @Column(name = "arrival" ,nullable = false)
    private String arrival;

    @Column(name = "arrival_location",columnDefinition = "text")
    private String arrivalLocation;

    @Column(name = "arrival_date",nullable = false)
    private LocalDate arrivalDate;

    @Column(name = "arrival_time",nullable = false)
    private LocalTime arrivalTime;

    @Column(name = "price",nullable = false)
    private Double price;

    @Column(name = "available_seat")
    private int availableSeat;

//    many to one bus
    @ManyToOne
    @JoinColumn(name = "bus_id",referencedColumnName = "id")
    private Bus bus;
}
