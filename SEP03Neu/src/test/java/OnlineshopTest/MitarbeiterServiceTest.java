package OnlineshopTest;

import Onlineshop.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;

public class MitarbeiterServiceTest {

    @Autowired
    private MitarbeiterRepository mitarbeiterRepository;

    @Autowired
    private SortimentRepository sortimentRepositoryLeer;


    @Autowired
    private Onlineshop.MitarbeiterService mitarbeiterService;

    @BeforeEach
    void setup(){mitarbeiterRepository.deleteAll();}

    @Test
    void istSortimentLeerTest(){
        Sortiment sortiment = new Sortiment("Hut",0);
        Sortiment sortimentTest = this.sortimentRepositoryLeer.findeAnzahl(0).get();
        sortimentTest.addSortiment(sortiment);
        sortimentRepositoryLeer.save(sortimentTest);
        assertThat(sortimentRepositoryLeer).isNotNull();
    }
//
//    @Transactional
//    public void istSortimentLeer(String name, Sortiment sortiment) throws SortimentIstLeerException {
//        Sortiment sortiment1 = this.sortimentRepositoryLeer.findeMitName(name).orElseThrow(() -> {
//            return new SortimentIstLeerException(name);
//        });
//        if (sortiment.getAnzahl() <= 0) {
//            sortiment1.addSortiment(sortiment);
//            this.sortimentRepositoryLeer.save(sortiment1);
//        }
//
//    }
}
