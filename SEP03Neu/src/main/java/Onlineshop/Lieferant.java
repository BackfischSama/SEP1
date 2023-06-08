package Onlineshop;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Lieferant {
    private String name;
    private String ort;
    private List<Sortiment> Warenliste;
    private Long id;

    public Lieferant() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return this.id;
    }
}
