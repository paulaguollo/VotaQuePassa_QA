import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VotoTest {

    private Eleitor eleitor;
    private Candidato candidato;
    private Voto voto;

    @BeforeEach
    void setUp() {
        // Cria o eleitor e o candidato primeiro, porque o voto precisa dos dois
        eleitor = new Eleitor("Ana Rocha", "E001", 20, "ana@mail.com", "911000001");
        candidato = new Candidato("Joana Almeida", 42, "Trofa");
        voto = new Voto(eleitor, candidato);
    }

    @Test
    void testGetEleitor() {
        assertEquals(eleitor, voto.getEleitor());
    }

    @Test
    void testGetCandidato() {
        assertEquals(candidato, voto.getCandidato());
    }
}