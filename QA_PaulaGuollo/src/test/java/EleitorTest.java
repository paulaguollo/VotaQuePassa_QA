import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EleitorTest {

    private Eleitor eleitor;

    @BeforeEach
    void setUp() {
        // Cria um eleitor de teste antes de cada teste correr
        eleitor = new Eleitor("Ana Rocha", "E001", 20, "ana@mail.com", "911000001");
    }

    @Test
    void testGetNome() {
        assertEquals("Ana Rocha", eleitor.getNome());
    }

    @Test
    void testGetNumeroIdentificacao() {
        assertEquals("E001", eleitor.getNumeroIdentificacao());
    }

    @Test
    void testGetIdade() {
        assertEquals(20, eleitor.getIdade());
    }

    @Test
    void testGetEmail() {
        assertEquals("ana@mail.com", eleitor.getEmail());
    }

    @Test
    void testGetTelemovel() {
        assertEquals("911000001", eleitor.getTelemovel());
    }
}