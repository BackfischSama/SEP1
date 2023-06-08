package OnlineshopTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;



import Onlineshop.Kunde;
import Onlineshop.KundenExistiertBereitsException;
import Onlineshop.KundenRepository;
import Onlineshop.KundenService;



public class KundenServiceTest {
    private KundenService kundenService;
    private KundenRepository kundenRepository;



    @BeforeEach
    public void setUp() {
        kundenRepository = mock(KundenRepository.class);
        kundenService = new KundenService(kundenRepository);
    }



    @Test
    public void testRegistrieren_WithValidData() throws KundenExistiertBereitsException {
        // Arrange
        String name = "John Doe";
        String email = "johndoe@example.com";
        String password = "password123";
        Kunde kunde = new Kunde(name, email, password);



        when(kundenRepository.save(any(Kunde.class))).thenReturn(kunde);



        // Act
        Kunde result = kundenService.registrieren(name, email, password);



        // Assert
        assertNotNull(result);
        assertEquals(name, result.getName());
        assertEquals(email, result.getEmail());
        assertEquals(password, result.getPassword());
        verify(kundenRepository, times(1)).save(any(Kunde.class));
    }



    @Test
    public void testRegistrieren_WithExistingEmail() {
        // Arrange
        String name = "John Doe";
        String email = "johndoe@example.com";
        String password = "password123";



        when(kundenRepository.existsByEmail(email)).thenReturn(true);



        // Act
        KundenExistiertBereitsException exception = assertThrows(KundenExistiertBereitsException.class, () ->
                kundenService.registrieren(name, email, password));



        // Assert
        assertEquals("Ein Kunde mit der E-Mail-Adresse johndoe@example.com existiert bereits.", exception.getMessage());
        verify(kundenRepository, times(0)).save(any(Kunde.class));
    }
}
