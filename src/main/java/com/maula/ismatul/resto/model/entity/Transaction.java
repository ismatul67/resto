package com.maula.ismatul.resto.model.entity;

import com.maula.ismatul.resto.enums.PaymentMethod;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @SequenceGenerator(name = "seq_transaction", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_transaction")
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    private PaymentMethod paymentMethod;

    private BigDecimal totalAmount;

    @Lob
    private String menu;
}
