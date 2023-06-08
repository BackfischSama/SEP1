package Onlineshop;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
public class Kunde {
    private String name;
    private String starssenname;
    private String hausnummer;
    private Integer postleitzahl;
    private String ort;
    @Enumerated(EnumType.STRING)
    private Land land;
    @Id
    private Long id;

    public Kunde() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }
}
