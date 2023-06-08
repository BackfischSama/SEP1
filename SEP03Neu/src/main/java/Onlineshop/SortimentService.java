package Onlineshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SortimentService {
    @Autowired
    private SortimentRepository sortimentRepository;

    public SortimentService() {
    }

    public Sortiment findeSortimentMitNamen(String name) throws SortimentIstLeerException {
        return (Sortiment)this.sortimentRepository.findeMitName(name).orElseThrow(() -> {
            return new SortimentIstLeerException(name);
        });
    }
}
