package Onlineshop;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Getter
@Entity
public class Sortiment implements Comparable {
    private String name;
    private Long kosten;
    private Integer anzahl;
    private String marke;
    private String beschreibung;
    private Long id;
    @OneToMany(
            cascade = {CascadeType.ALL},
            fetch = FetchType.EAGER
    )
    private List<Sortiment> sortiment = new ArrayList();

    public Sortiment() {
    }

    public Sortiment(String name) {
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return this.id;
    }

    public Sortiment(String name, Integer anzahl){
        this.name = name;
        this.anzahl = anzahl;
    }

    public Sortiment(String name, Long kosten, Integer anzahl, String marke, String beschreibung, Long id) {
        this.name = name;
        this.kosten = kosten;
        this.anzahl = anzahl;
        this.marke = marke;
        this.beschreibung = beschreibung;
        this.id = id;
    }

    public int compareTo(Sortiment objekt) {
        int compareErgebnis = this.name.compareTo(objekt.name);
        return compareErgebnis == 0 ? this.name.compareTo(objekt.name) : compareErgebnis;
    }

    public void addSortiment(Sortiment sortiment) {
        this.sortiment.add(sortiment);
    }

    public int compareTo(Object o) {
        return 0;
    }

    public String getName() {
        return this.name;
    }

    public Long getKosten() {
        return this.kosten;
    }

    public Integer getAnzahl() {
        return this.anzahl;
    }

    public String getMarke() {
        return this.marke;
    }

    public String getBeschreibung() {
        return this.beschreibung;
    }

    public List<Sortiment> getSortiment() {
        return this.sortiment;
    }
}