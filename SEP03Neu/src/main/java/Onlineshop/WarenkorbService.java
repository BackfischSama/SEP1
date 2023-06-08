package Onlineshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WarenkorbService {
    @Autowired
    private WarenkorbRepository warenkorbRepository;

    public WarenkorbService(WarenkorbRepository warenkorbRepository) {
        this.warenkorbRepository = warenkorbRepository;
    }

    @Transactional
    public void WareHinzufuegen(String name, Sortiment sortiment) throws WarenkorbIstLeerExeption {
        Warenkorb warenkorb = (Warenkorb)this.warenkorbRepository.findeMitNamen(name).orElseThrow(() -> {
            return new WarenkorbIstLeerExeption(name);
        });
        warenkorb.addSortiment(sortiment);
        this.warenkorbRepository.save(warenkorb);
    }

    @Transactional
    public void WareKaufen(){
        this.warenkorbRepository.deleteAll();
    }
}
