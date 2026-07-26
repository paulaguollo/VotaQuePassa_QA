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

    // Verifica o nome
    @Test
    void testGetNome() {
        assertEquals("Eleicoes da cidade da Paulinha", eleicao.getNome());
    }

    // Verifica a idade
    @Test
    void testGetIdadeMinima() {
        assertEquals(18, eleicao.getIdadeMinima());
    }

    // Requisito 5: candidato com idade acima da minima deve ser adicionado com sucesso
    @Test
    void testAdicionarCandidatoValido() {
        eleicao.adicionarCandidato(tedesco);
        assertTrue(eleicao.getCandidatos().contains(tedesco));
    }

    // Requisito 5: candidato com idade abaixo da idade minima nao pode ser adicionado
    @Test
    void testAdicionarCandidatoNaoElegivel() {
        assertThrows(IllegalArgumentException.class, () -> eleicao.adicionarCandidato(yago));
    }

    // Requisito 5: candidato com idade exatamente igual a idade minima deve ser aceite
    @Test
    void testAdicionarCandidatoIdadeLimite() {
        eleicao.adicionarCandidato(nuno);
        assertTrue(eleicao.getCandidatos().contains(nuno));
    }

    // Requisito 2: voto valido - eleitor com idade valida, candidato registado na eleicao
    @Test
    void testVotarValido() {
        eleicao.adicionarCandidato(tedesco);
        eleicao.votar(vitor, tedesco);
        assertEquals(1, eleicao.getVotos().size());
    }

    // Requisito 2 e 5: eleitor abaixo da idade minima nao pode votar
    @Test
    void testVotarEleitorAbaixoIdadeMinima() {
        eleicao.adicionarCandidato(tedesco);
        assertThrows(IllegalArgumentException.class, () -> eleicao.votar(bruninho, tedesco));
    }

    // Requisito 2 e 5: eleitor com idade exatamente igual a idade minima deve poder votar
    @Test
    void testVotarEleitorIdadeLimite() {
        eleicao.adicionarCandidato(tedesco);
        eleicao.votar(vitorLimite, tedesco);
        assertEquals(1, eleicao.getVotos().size());
    }

    // Requisito 2: nao pode votar num candidato que nao esta registado na eleicao
    @Test
    void testVotarCandidatoNaoRegistado() {
        assertThrows(IllegalArgumentException.class, () -> eleicao.votar(vitor, bolsonisto));
    }

    // Requisito 1: um eleitor so pode votar uma vez por eleicao
    @Test
    void testVotarDuasVezes() {
        eleicao.adicionarCandidato(tedesco);
        eleicao.votar(vitor, tedesco);
        assertThrows(IllegalArgumentException.class, () -> eleicao.votar(vitor, tedesco));
    }

    // Requisito 4 (contagem): a contagem de votos por candidato deve refletir os votos validos
    @Test
    void testContarVotos() {
        eleicao.adicionarCandidato(tedesco);
        eleicao.votar(vitor, tedesco);
        eleicao.votar(sara, tedesco);
        assertEquals(2, eleicao.contarVotos(tedesco));
    }

    // Requisito 5 (vencedor): o vencedor e o candidato com mais votos
    @Test
    void testObterVencedorComVencedorClaro() {
        eleicao.adicionarCandidato(tedesco);
        eleicao.adicionarCandidato(lula);
        eleicao.votar(vitor, tedesco);
        eleicao.votar(sara, tedesco);
        assertEquals(tedesco, eleicao.obterVencedor());
    }

    // Requisito 5 (empate): em caso de empate, obterVencedor deve devolver null
    @Test
    void testObterVencedorEmpate() {
        eleicao.adicionarCandidato(tedesco);
        eleicao.adicionarCandidato(lula);
        eleicao.votar(vitor, tedesco);
        eleicao.votar(sara, lula);
        assertNull(eleicao.obterVencedor());
    }

    // Requisito 6: deve ser possivel obter a lista de candidatos e a lista de votos registados
    @Test
    void testGetCandidatosEGetVotosListas() {
        eleicao.adicionarCandidato(tedesco);
        eleicao.adicionarCandidato(lula);
        eleicao.votar(vitor, tedesco);

        assertEquals(2, eleicao.getCandidatos().size());
        assertEquals(1, eleicao.getVotos().size());
    }
}