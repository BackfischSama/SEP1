package Onlineshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MitarbeiterService {
    @Autowired
    private SortimentRepository sortimentRepositoryLeer;

    public MitarbeiterService() {
    }

    @Transactional
    public void istSortimentLeer(String name, Sortiment sortiment) throws SortimentIstLeerException {
        Sortiment sortiment1 = (Sortiment)this.sortimentRepositoryLeer.findeMitName(name).orElseThrow(() -> {
            return new SortimentIstLeerException(name);
        });
        if (sortiment.getAnzahl() <= 0) {
            sortiment1.addSortiment(sortiment);
            this.sortimentRepositoryLeer.save(sortiment1);
        }

    }

    public SortimentRepository getSortimentRepositoryLeer() {
        return this.sortimentRepositoryLeer;
    }

    public void setSortimentRepositoryLeer(SortimentRepository sortimentRepository) {
    }
}