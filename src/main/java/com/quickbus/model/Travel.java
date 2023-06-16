package com.quickbus.model;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
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

    @NotBlank
    @Column(name = "departure",nullable = false)
    private String departure;

    @NotBlank
    @Column(name = "departure_location",columnDefinition = "text")
    private String departureLocation;

    @NotNull
    @Column(name = "departure_date",nullable = false)
    private LocalDate departureDate;

    @NotNull
    @Column(name = "departure_time",nullable = false)
    private LocalTime departureTime;

    @NotBlank
    @Column(name = "arrival" ,nullable = false)
    private String arrival;

    @NotBlank
    @Column(name = "arrival_location",columnDefinition = "text")
    private String arrivalLocation;

    @NotNull
    @Column(name = "arrival_date",nullable = false)
    private LocalDate arrivalDate;

    @NotNull
    @Column(name = "arrival_time",nullable = false)
    private LocalTime arrivalTime;

    @Positive
    @Column(name = "price",nullable = false)
    private Double price;

    @Column(name = "available_seat")
    private int availableSeat;

//    many to one bus
    @ManyToOne
    @JoinColumn(name = "bus_id",referencedColumnName = "id")
    private Bus bus;
}
