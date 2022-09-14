package com.maula.ismatul.resto.model.entity;

import lombok.*;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "food")
public class Food {

    @Id
    @SequenceGenerator(name = "seq_food", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_food")
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "name", length = 50,  nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "text",  nullable = false)
    private String description;

    @Column(name = "price",  nullable = false)
    private BigDecimal price;

    private boolean isDelete;
    
}
