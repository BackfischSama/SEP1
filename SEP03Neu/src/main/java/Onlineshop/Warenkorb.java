package Onlineshop;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Warenkorb {
    @OneToMany(
            cascade = {CascadeType.ALL},
            fetch = FetchType.EAGER
    )
    private List<Sortiment> sortiment = new ArrayList();
    private Integer Anzahl;
    private Long kosten;
    private String gutschein;
    private Long id;

    public Warenkorb() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Warenkorb(List<Sortiment> markierteWare, Integer anzahl, Long kosten, String gutschein, Long id) {
        this.sortiment = markierteWare;
        this.Anzahl = anzahl;
        this.kosten = kosten;
        this.gutschein = gutschein;
        this.id = id;
    }

    @Id
    public Long getId() {
        return this.id;
    }

    public void addSortiment(Sortiment sortiment) {
        this.sortiment.add(sortiment);
    }
}
