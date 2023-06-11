package com.quickbus.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Table(name = "passenger")
public class Passenger implements Serializable {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "nik")
    private String nik;

//    many to one ticket
    @ManyToOne(targetEntity = Ticket.class)
    @JoinColumn(name = "ticket_id",referencedColumnName = "id")
    private Ticket ticket;
}