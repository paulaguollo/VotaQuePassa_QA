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

    @Test
    void testAdicionarCandidatoValido() {
        eleicao.adicionarCandidato(candidato);
        assertTrue(eleicao.getCandidatos().contains(candidato));
    }

    @Test
    void testAdicionarCandidatoNaoElegivel() {
        Candidato candidatoJovem = new Candidato("Rui Jovem", 10, "Braga");
        assertThrows(IllegalArgumentException.class, () -> eleicao.adicionarCandidato(candidatoJovem));
    }

    @Test
    void testAdicionarCandidatoIdadeLimite() {
        Candidato candidatoLimite = new Candidato("No Limite", 18, "Porto");
        eleicao.adicionarCandidato(candidatoLimite);
        assertTrue(eleicao.getCandidatos().contains(candidatoLimite));
    }

    @Test
    void testVotarValido() {
        eleicao.adicionarCandidato(candidato);
        eleicao.votar(eleitor, candidato);
        assertEquals(1, eleicao.getVotos().size());
    }

    @Test
    void testVotarEleitorAbaixoIdadeMinima() {
        Eleitor eleitorMenor = new Eleitor("Carla Nova", "E999", 15, "carla@mail.com", "911999999");
        eleicao.adicionarCandidato(candidato);
        assertThrows(IllegalArgumentException.class, () -> eleicao.votar(eleitorMenor, candidato));
    }

    @Test
    void testVotarEleitorIdadeLimite() {
        Eleitor eleitorLimite = new Eleitor("No Limite", "E998", 18, "limite@mail.com", "911999998");
        eleicao.adicionarCandidato(candidato);
        eleicao.votar(eleitorLimite, candidato);
        assertEquals(1, eleicao.getVotos().size());
    }

    // Requisito 2: nao pode votar num candidato que nao esta registado na eleicao
    @Test
    void testVotarCandidatoNaoRegistado() {
        Candidato candidatoForaDaEleicao = new Candidato("Fora", 40, "Aveiro");
        assertThrows(IllegalArgumentException.class, () -> eleicao.votar(eleitor, candidatoForaDaEleicao));
    }

    @Test
    void testVotarDuasVezes() {
        eleicao.adicionarCandidato(candidato);
        eleicao.votar(eleitor, candidato);
        assertThrows(IllegalArgumentException.class, () -> eleicao.votar(eleitor, candidato));
    }

    @Test
    void testContarVotos() {
        Eleitor eleitor2 = new Eleitor("Joao Novo", "E997", 25, "joao@mail.com", "911999997");
        eleicao.adicionarCandidato(candidato);
        eleicao.votar(eleitor, candidato);
        eleicao.votar(eleitor2, candidato);
        assertEquals(2, eleicao.contarVotos(candidato));
    }

    @Test
    void testObterVencedorComVencedorClaro() {
        Candidato candidato2 = new Candidato("Ricardo Meireles", 50, "Maia");
        Eleitor eleitor2 = new Eleitor("Joao Novo", "E997", 25, "joao@mail.com", "911999997");
        eleicao.adicionarCandidato(candidato);
        eleicao.adicionarCandidato(candidato2);
        eleicao.votar(eleitor, candidato);
        eleicao.votar(eleitor2, candidato);
        assertEquals(candidato, eleicao.obterVencedor());
    }

    @Test
    void testObterVencedorEmpate() {
        Candidato candidato2 = new Candidato("Ricardo Meireles", 50, "Maia");
        Eleitor eleitor2 = new Eleitor("Joao Novo", "E997", 25, "joao@mail.com", "911999997");
        eleicao.adicionarCandidato(candidato);
        eleicao.adicionarCandidato(candidato2);
        eleicao.votar(eleitor, candidato);
        eleicao.votar(eleitor2, candidato2);
        assertNull(eleicao.obterVencedor());
    }

    @Test
    void testGetCandidatosEGetVotosListas() {
        Candidato candidato2 = new Candidato("Ricardo Meireles", 50, "Maia");
        eleicao.adicionarCandidato(candidato);
        eleicao.adicionarCandidato(candidato2);
        eleicao.votar(eleitor, candidato);

        assertEquals(2, eleicao.getCandidatos().size());
        assertEquals(1, eleicao.getVotos().size());
    }
}