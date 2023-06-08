package OnlineshopTest;

import Onlineshop.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;

public class SortimentServiceTest {

    @Autowired
    private SortimentRepository sortimentRepository;

    @Autowired
    private SortimentService sortimentService;

    @BeforeEach
    void setup(){sortimentRepository.deleteAll();}

    @Test
    void findeSortimentMitNamenTest() throws SortimentIstLeerException {
        Sortiment sortimenta = new Sortiment("Apfel");
        Sortiment sortimentb = new Sortiment("Baum");
        sortimentRepository.save(sortimentb);
        sortimentRepository.save(sortimenta);
        sortimenta.addSortiment(sortimentb);
        assertThat(sortimentService.findeSortimentMitNamen("Baum")).isNotNull();

    }

//    public Sortiment findeSortimentMitNamen(String name) throws SortimentIstLeerException {
//        return (Sortiment)this.sortimentRepository.findeMitName(name).orElseThrow(() -> {
//            return new SortimentIstLeerException(name);
//        });
//    }
}
