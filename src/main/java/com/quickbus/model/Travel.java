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
@Table(name="travel")
public class Travel implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(name = "departure")
    private String departure;

    @Column(name = "departure_location",columnDefinition = "text")
    private String departureLocation;

    @Column(name = "departure_at",columnDefinition = "timestamp")
    private Date departureAt;

    @Column(name = "arrival")
    private String arrival;

    @Column(name = "arrival_location",columnDefinition = "text")
    private String arrivalLocation;

    @Column(name = "arrival_at",columnDefinition = "timestamp")
    private Date arrivalAt;

    @Column(name = "duration")
    private String duration;

    @Column(name = "price")
    private Double price;

    @Column(name = "available_seat")
    private int availableSeat;

    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;

    @Column(name = "deleted_at")
    private Date deletedAt;

//    many to one bus
    @ManyToOne
    @JoinColumn(name = "bus_id",referencedColumnName = "id")
    private Bus bus;
}
