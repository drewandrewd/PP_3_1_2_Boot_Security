package ru.kata.spring.boot_security.demo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "stock_movements")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Product product;

    private int quantity;
    private String movementType;
    private LocalDate movementDate;

    @ManyToOne
    private User user;

    @ManyToOne(optional = true)
    private Supplier supplier;

    @ManyToOne(optional = true)
    private Recipient recipient;

    public User getResponsibleUser() {
        return user;
    }
}
