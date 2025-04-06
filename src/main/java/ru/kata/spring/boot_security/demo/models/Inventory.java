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
import javax.persistence.Transient;
import java.time.LocalDate;

@Entity
@Table(name = "inventories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate inventoryDate;
    private String zone;
    private String shelf;
    private String remarks;

    @ManyToOne
    private User user;

    @Transient
    public Location getLocation() {
        Location loc = new Location();
        loc.setZone(this.zone);
        loc.setShelf(this.shelf);
        return loc;
    }

    public String getComment() {
        return remarks;
    }
}
