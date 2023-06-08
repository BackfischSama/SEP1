package OnlineshopTest;

import Onlineshop.KundenRepository;
import Onlineshop.KundenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;

public class KundenServiceTest {

    @Autowired
    private KundenRepository kundenRepository;

    @Autowired
    @OneToMany(
            cascade = {CascadeType.ALL},
            fetch = FetchType.EAGER
    )
    private ArrayList sortiment = new ArrayList();

    @Autowired
    private KundenService kundenService;

    @BeforeEach
    void setup(){kundenRepository.deleteAll();}

    @Test
    void Bacic(){
        assertThat(1).isEqualTo(1);
    }
}
