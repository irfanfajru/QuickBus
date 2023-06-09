package com.investree.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.investree.demo.model.oauth.User;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name="transaksi")
public class Transaksi implements Serializable {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="tenor",nullable = false)
    private int tenor;

    @Column(name="total_pinjaman",nullable = false)
    private Double totalPinjaman;

    @Column(name="bunga_persen",nullable = false)
    private Double bungaPersen;

    @Column(name="status",nullable = false)
    private String status;

//    many to one user peminjam
    @ManyToOne
    @JoinColumn(name = "id_peminjam",referencedColumnName = "id")
    private User peminjam;

//    many to one user meminjam
    @ManyToOne
    @JoinColumn(name="id_meminjam", referencedColumnName = "id")
    private User meminjam;

//    One to many payment_history
    @JsonIgnore
    @OneToMany(mappedBy = "transaksi",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PaymentHistory> paymentHistory;

}
