import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CandidatoTest {

    private Candidato candidato;

    @BeforeEach
    void setUp() {
        candidato = new Candidato("Paula", 30, "Lisboa");
    }

    // Verifica o nome
    @Test
    void testGetNome() {
        assertEquals("Paula", candidato.getNome());
    }

    // Verifica a idade
    @Test
    void testGetIdade() {
        assertEquals(30, candidato.getIdade());
    }

    // Verifica a naturalidade
    @Test
    void testGetNaturalidade() {
        assertEquals("Lisboa", candidato.getNaturalidade());
    }

    //Confirma que os getters funcionam corretamente com outros dados
    @Test
    void testGetterComOutrosValores() {
        Candidato outroCandidato = new Candidato("Jorginho Santos", 50, "Porto");
        assertEquals("Jorginho Santos", outroCandidato.getNome());
        assertEquals(50, outroCandidato.getIdade());
        assertEquals("Porto", outroCandidato.getNaturalidade());
    }

    // Teste de idade negativa
    @Test
    void testConstrutorAceitaIdadeNegativaSemValidacao() {
        Candidato candidatoInvalido = new Candidato("Teste", -10, "Coimbra");
        assertEquals(-10, candidatoInvalido.getIdade());
    }
}