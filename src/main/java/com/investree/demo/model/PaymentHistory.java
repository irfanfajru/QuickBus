package com.investree.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name="payment_history")
public class PaymentHistory implements Serializable {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="pembayaran_ke",nullable = false)
    private int pembayaranKe;

    @Column(name="jumlah",nullable = false)
    private Double jumlah;

    @Column(name="bukti_pembayaran",nullable = false)
    private String buktiPembayaran;

//    Many to one Transaksi
    @ManyToOne
    @JoinColumn(name="id_transaksi",referencedColumnName = "id")
    private Transaksi transaksi;
}
