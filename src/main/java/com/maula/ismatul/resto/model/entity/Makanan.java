package com.maula.ismatul.resto.model.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "makanan")
public class Makanan {

    @Id
    @SequenceGenerator(name = "seq_makanan", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_makanan")
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "nama", length = 50,  nullable = false)
    private String nama;

    @Column(name = "deskripsi", columnDefinition = "text",  nullable = false)
    private String deskripsi;

    @Column(name = "harga",  nullable = false)
    private BigDecimal harga;

    @OneToMany(mappedBy = "makanan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservasi;
    
}
