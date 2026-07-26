import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EleitorTest {

    private Eleitor eleitor;

    @BeforeEach
    void setUp() {
        eleitor = new Eleitor("Bia", "E001", 20, "bia@mail.com", "911003201");
    }

    // Verifica o nome
    @Test
    void testGetNome() {
        assertEquals("Bia", eleitor.getNome());
    }

    // Verifica o numero de identificacao
    @Test
    void testGetNumeroIdentificacao() {
        assertEquals("E001", eleitor.getNumeroIdentificacao());
    }

    // Verifica a idade
    @Test
    void testGetIdade() {
        assertEquals(20, eleitor.getIdade());
    }

    // Verifica o email
    @Test
    void testGetEmail() {
        assertEquals("bia@mail.com", eleitor.getEmail());
    }

    // Verifica o telemovel
    @Test
    void testGetTelemovel() {
        assertEquals("911003201", eleitor.getTelemovel());
    }

    // Confirma se funcionam corretamente com outros dados
    @Test
    void testGetterComOutrosValores() {
        Eleitor outroEleitor = new Eleitor("Joao Rasta", "E002", 17, "joao@mail.com", "911000252");
        assertEquals("Joao Rasta", outroEleitor.getNome());
        assertEquals("E002", outroEleitor.getNumeroIdentificacao());
        assertEquals(17, outroEleitor.getIdade());
        assertEquals("joao@mail.com", outroEleitor.getEmail());
        assertEquals("911000252", outroEleitor.getTelemovel());
    }

    // Teste para ver se aceita idade negativa
    @Test
    void testConstrutorAceitaIdadeNegativaSemValidacao() {
        Eleitor eleitorInvalido = new Eleitor("Teste", "E01", -30, "teste@mail.com", "9119912345");
        assertEquals(-30, eleitorInvalido.getIdade());
    }
}