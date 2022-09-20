package com.maula.ismatul.resto.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "bill")
public class Bill {

    @Id
    @SequenceGenerator(name = "seq_bill", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_bil")
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "name", updatable = false)
    private String name;

    private Boolean isPaid;

    @JsonIgnoreProperties(value = {"bill"})
    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL)
    private List<BillDetail> orderDetails;

}
