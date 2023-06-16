package com.quickbus.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Table(name = "passenger")
public class Passenger implements Serializable {
    @Id
    @Column(name="id",nullable = false,insertable = false,updatable = false)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "nik",nullable = false)
    private String nik;

//    many to one ticket
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ticket_id",referencedColumnName = "id")
    private Ticket ticket;
}
