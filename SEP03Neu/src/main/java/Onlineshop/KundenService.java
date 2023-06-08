package Onlineshop;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class KundenService {
    @Autowired
    private KundenRepository kundenRepository;
    @OneToMany(
            cascade = {CascadeType.ALL},
            fetch = FetchType.EAGER
    )
    private List<Sortiment> sortiment = new ArrayList();

    public KundenService() {
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
}
