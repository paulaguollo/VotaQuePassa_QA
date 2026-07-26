import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VotoTest {

    private Eleitor eleitor;
    private Candidato candidato;
    private Voto voto;

    @BeforeEach
    void setUp() {
        eleitor = new Eleitor("Bia", "E001", 20, "bia@mail.com", "911003201");
        candidato = new Candidato("Paula", 30, "Lisboa");
        voto = new Voto(eleitor, candidato);
    }

    // Verifica se o getter do eleitor devolve o eleitor que foi usado para criar o voto
    @Test
    void testGetEleitor() {
        assertEquals(eleitor, voto.getEleitor());
    }

    // Verifica se o getter do candidato devolve o candidato que foi usado para criar o voto
    @Test
    void testGetCandidato() {
        assertEquals(candidato, voto.getCandidato());
    }

    // Confirma que o voto guarda exatamente os dados passados. Nao cria copias do eleitor nem do candidato
    @Test
    void testVotoGuardaReferenciasExatas() {
        assertSame(eleitor, voto.getEleitor());
        assertSame(candidato, voto.getCandidato());
    }
}