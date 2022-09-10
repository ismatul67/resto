package com.maula.ismatul.resto.model.entity;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "transaksi")
public class Transaksi {
    
    @Id
    @SequenceGenerator(name = "seq_transaksi", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_transaksi")
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @OneToMany(mappedBy = "transaksi", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservasi;
}
