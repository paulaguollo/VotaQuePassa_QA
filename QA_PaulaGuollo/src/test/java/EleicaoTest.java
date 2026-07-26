import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EleicaoTest {

    private Eleicao eleicao;

    private Candidato tedesco;      // candidato principal, usado na maioria dos testes
    private Candidato lula;         // segundo candidato, para contagem/vencedor/empate
    private Candidato yago;         // jovem para a idade minima
    private Candidato nuno;         // idade == idade minima
    private Candidato bolsonisto;   // nunca e registado na eleicao

    private Eleitor vitor;          // eleitor principal, valido
    private Eleitor sara;           // segundo eleitor valido, para contagem/vencedor
    private Eleitor bruninho;       // menor de idade
    private Eleitor vitorLimite;    // idade == idade minima

    @BeforeEach
    void setUp() {
        eleicao = new Eleicao("Eleicoes da cidade da Paulinha", 18);

        tedesco = new Candidato("Tedesco", 23, "Alemanha");
        lula = new Candidato("Lula", 60, "Brasil");
        yago = new Candidato("Yago", 10, "Etiopia");
        nuno = new Candidato("Nuno", 18, "Portugal");
        bolsonisto = new Candidato("Bolsonisto", 70, "Brasil");

        vitor = new Eleitor("Vitor Maravilhoso Santos", "E001", 27, "vitor@mail.com", "911000001");
        sara = new Eleitor("Sara", "E002", 33, "sara@mail.com", "911000002");
        bruninho = new Eleitor("Bruninho Santos", "E003", 15, "bruninho@mail.com", "911000003");
        vitorLimite = new Eleitor("Vitor Maravilhoso Santos", "E004", 18, "vitor.limite@mail.com", "911000004");
    }

    @Test
    void testGetNome() {
        assertEquals("Eleicoes da cidade da Paulinha", eleicao.getNome());
    }

    @Test
    void testGetIdadeMinima() {
        assertEquals(18, eleicao.getIdadeMinima());
    }

    @Test
    void testAdicionarCandidatoValido() {
        eleicao.adicionarCandidato(tedesco);
        assertTrue(eleicao.getCandidatos().contains(tedesco));
    }

    @Test
    void testAdicionarCandidatoNaoElegivel() {
        assertThrows(IllegalArgumentException.class, () -> eleicao.adicionarCandidato(yago));
    }

    @Test
    void testAdicionarCandidatoIdadeLimite() {
        eleicao.adicionarCandidato(nuno);
        assertTrue(eleicao.getCandidatos().contains(nuno));
    }

    @Test
    void testVotarValido() {
        eleicao.adicionarCandidato(tedesco);
        eleicao.votar(vitor, tedesco);
        assertEquals(1, eleicao.getVotos().size());
    }

    @Test
    void testVotarEleitorAbaixoIdadeMinima() {
        eleicao.adicionarCandidato(tedesco);
        assertThrows(IllegalArgumentException.class, () -> eleicao.votar(bruninho, tedesco));
    }

    @Test
    void testVotarEleitorIdadeLimite() {
        eleicao.adicionarCandidato(tedesco);
        eleicao.votar(vitorLimite, tedesco);
        assertEquals(1, eleicao.getVotos().size());
    }

    @Test
    void testVotarCandidatoNaoRegistado() {
        assertThrows(IllegalArgumentException.class, () -> eleicao.votar(vitor, bolsonisto));
    }

    @Test
    void testVotarDuasVezes() {
        eleicao.adicionarCandidato(tedesco);
        eleicao.votar(vitor, tedesco);
        assertThrows(IllegalArgumentException.class, () -> eleicao.votar(vitor, tedesco));
    }

    @Test
    void testContarVotos() {
        eleicao.adicionarCandidato(tedesco);
        eleicao.votar(vitor, tedesco);
        eleicao.votar(sara, tedesco);
        assertEquals(2, eleicao.contarVotos(tedesco));
    }

    @Test
    void testObterVencedorComVencedorClaro() {
        eleicao.adicionarCandidato(tedesco);
        eleicao.adicionarCandidato(lula);
        eleicao.votar(vitor, tedesco);
        eleicao.votar(sara, tedesco);
        assertEquals(tedesco, eleicao.obterVencedor());
    }

    @Test
    void testObterVencedorEmpate() {
        eleicao.adicionarCandidato(tedesco);
        eleicao.adicionarCandidato(lula);
        eleicao.votar(vitor, tedesco);
        eleicao.votar(sara, lula);
        assertNull(eleicao.obterVencedor());
    }

    @Test
    void testGetCandidatosEGetVotosListas() {
        eleicao.adicionarCandidato(tedesco);
        eleicao.adicionarCandidato(lula);
        eleicao.votar(vitor, tedesco);

        assertEquals(2, eleicao.getCandidatos().size());
        assertEquals(1, eleicao.getVotos().size());
    }
}