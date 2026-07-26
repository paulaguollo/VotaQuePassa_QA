import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CandidatoTest {

    private Candidato candidato;

    @BeforeEach
    void setUp() {
        candidato = new Candidato("Paula", 30, "Lisboa");
    }

    @Test
    void testGetNome() {
        assertEquals("Paula", candidato.getNome());
    }

    @Test
    void testGetIdade() {
        assertEquals(30, candidato.getIdade());
    }

    @Test
    void testGetNaturalidade() {
        assertEquals("Lisboa", candidato.getNaturalidade());
    }

    @Test
    void testGetterComOutrosValores() {
        Candidato outroCandidato = new Candidato("Jorginho Santos", 50, "Porto");
        assertEquals("Jorginho Santos", outroCandidato.getNome());
        assertEquals(50, outroCandidato.getIdade());
        assertEquals("Porto", outroCandidato.getNaturalidade());
    }

    @Test
    void testConstrutorAceitaIdadeNegativaSemValidacao() {
        Candidato candidatoInvalido = new Candidato("Teste", -10, "Coimbra");
        assertEquals(-10, candidatoInvalido.getIdade());
    }
}