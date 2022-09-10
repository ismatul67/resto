package com.maula.ismatul.resto.model.entity;
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
@Table(name = "reservation")
public class Reservation {
    
    @Id
    @SequenceGenerator(name = "seq_reservation", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_reservation")
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Makanan makanan;

    private Integer quantity;

}
