package com.maula.ismatul.resto.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "bill_detail")
public class BillDetail {
    @Id
    @SequenceGenerator(name = "seq_bill_detail", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_bill_detail")
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @JsonIgnoreProperties(value = {"orderDetails"})
    @ManyToOne(fetch = FetchType.LAZY)
    private Bill bill;

    @ManyToOne(fetch = FetchType.EAGER)
    private Food food;

    private Integer quantity;

}
