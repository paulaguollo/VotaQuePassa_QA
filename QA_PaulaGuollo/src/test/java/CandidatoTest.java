import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CandidatoTest {

    private Candidato candidato;

    @BeforeEach
    void setUp() {
        // Cria um candidato de teste antes de cada teste correr
        candidato = new Candidato("Joana Almeida", 42, "Trofa");
    }

    @Test
    void testGetNome() {
        assertEquals("Joana Almeida", candidato.getNome());
    }

    @Test
    void testGetIdade() {
        assertEquals(42, candidato.getIdade());
    }

    @Test
    void testGetNaturalidade() {
        assertEquals("Trofa", candidato.getNaturalidade());
    }


}