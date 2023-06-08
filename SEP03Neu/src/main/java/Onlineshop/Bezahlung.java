package Onlineshop;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
public class Bezahlung {

    @Enumerated(EnumType.STRING)
    private BezahlungsArt bezahlungsArt;
    private String Gutschein;
    private Long kosten;
    @Id
    private Long id;

    public Bezahlung() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }
}
