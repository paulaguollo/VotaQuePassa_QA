import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EleicaoTest {

    private Eleicao eleicao;
    private Candidato candidato;
    private Eleitor eleitor;

    @BeforeEach
    void setUp() {
        eleicao = new Eleicao("Eleicoes Autarquicas 2025", 18);
        candidato = new Candidato("Joana Almeida", 42, "Trofa");
        eleitor = new Eleitor("Ana Rocha", "E001", 20, "ana@mail.com", "911000001");
    }

    @Test
    void testGetNome() {
        assertEquals("Eleicoes Autarquicas 2025", eleicao.getNome());
    }

    @Test
    void testGetIdadeMinima() {
        assertEquals(18, eleicao.getIdadeMinima());
    }
}