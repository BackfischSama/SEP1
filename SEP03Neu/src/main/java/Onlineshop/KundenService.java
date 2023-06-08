package Onlineshop;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Getter
@Setter
@Service
public class KundenService {
    @Autowired
    private KundenRepository kundenRepository;
    @OneToMany(
            cascade = {CascadeType.ALL},
            fetch = FetchType.EAGER
    )
    private List<Sortiment> sortiment = new ArrayList();

    public KundenService(KundenRepository kundenRepository) {
    }

    @Transactional
    public void sortimentAnschauen(String name, List<Sortiment> sortimentenliste) throws SortimentIstLeerException {
        if (sortimentenliste.size() <= 0) {
            throw new SortimentIstLeerException(name);
        } else {
            for(int i = 0; i < sortimentenliste.size(); ++i) {
                Sortiment angeschautesTeil = (Sortiment)sortimentenliste.get(i);
                System.out.println(angeschautesTeil);
            }

        }
    }

    @Transactional
    public Kunde registrieren(String name, String straßenname, String hausnummer, Integer postleitzahl, String ort, Land land, String password) {
        Kunde kunde = new Kunde();
        kunde.setName(name);
        kunde.setStraßenname(straßenname);
        kunde.setHausnummer(hausnummer);
        kunde.setPostleitzahl(postleitzahl);
        kunde.setOrt(ort);
        kunde.setLand(land);
        kunde.setPassword(password);

        return kundenRepository.save(kunde);
    }
    public boolean existsByEmail(String email) {
        return KundenRepository.existsByEmail(email);
    }

    public Kunde registrieren(String name, EMail email, String password) {
        Kunde kunde = new Kunde();
//        EMail eMail = new EMail();
        kunde.setName(name);
        kunde.setEmail(email);
        kunde.setPassword(password);
    }
}
