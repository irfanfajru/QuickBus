package com.quickbus.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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

    @NotBlank
    @Column(name = "name",nullable = false)
    private String name;

    @NotBlank
    @Column(name = "nik",nullable = false)
    private String nik;

//    many to one ticket
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ticket_id",referencedColumnName = "id")
    private Ticket ticket;
}
