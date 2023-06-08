package OnlineshopTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;



import Onlineshop.Mitarbeiter;
import Onlineshop.MitarbeiterExistiertBereitsException;
import Onlineshop.MitarbeiterRepository;
import Onlineshop.MitarbeiterService;



public class MitarbeiterServiceTest {
    private MitarbeiterService mitarbeiterService;
    private MitarbeiterRepository mitarbeiterRepository;



    @BeforeEach
    public void setUp() {
        mitarbeiterRepository = mock(MitarbeiterRepository.class);
        mitarbeiterService = new MitarbeiterService(mitarbeiterRepository);
    }



    @Test
    public void testRegistrieren_WithValidData() throws MitarbeiterExistiertBereitsException {
        // Arrange
        String name = "John Doe";
        String email = "johndoe@example.com";
        String password = "password123";
        Mitarbeiter mitarbeiter = new Mitarbeiter(name, email, password);



        when(mitarbeiterRepository.save(any(Mitarbeiter.class))).thenReturn(mitarbeiter);



        // Act
        Mitarbeiter result = mitarbeiterService.registrieren(name, email, password);



        // Assert
        assertNotNull(result);
        assertEquals(name, result.getName());
        assertEquals(email, result.getEmail());
        assertEquals(password, result.getPassword());
        verify(mitarbeiterRepository, times(1)).save(any(Mitarbeiter.class));
    }



    @Test
    public void testRegistrieren_WithExistingEmail() {
        // Arrange
        String name = "John Doe";
        String email = "johndoe@example.com";
        String password = "password123";



        when(mitarbeiterRepository.existsByEmail(email)).thenReturn(true);



        // Act
        MitarbeiterExistiertBereitsException exception = assertThrows(MitarbeiterExistiertBereitsException.class, () ->
                mitarbeiterService.registrieren(name, email, password));



        // Assert
        assertEquals("Ein Mitarbeiter mit der E-Mail-Adresse johndoe@example.com existiert bereits.", exception.getMessage());
        verify(mitarbeiterRepository, times(0)).save(any(Mitarbeiter.class));
    }
}