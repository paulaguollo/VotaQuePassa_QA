# Vota Que Passa

Trabalho prático de Quality Assurance com testes unitários em Java com JUnit 5 para um sistema de simulação de eleições.

## Contexto

O sistema simula o funcionamento de eleições, com registo de candidatos e eleitores, e registo de votos válidos, respeitando regras de idade mínima e unicidade do voto por eleitor. É composto por 4 classes principais:

- **Eleitor** — representa um cidadão com direito a voto
- **Candidato** — representa um cidadão elegível como candidato
- **Voto** — representa um voto lançado por um eleitor num candidato
- **Eleicao** — representa o processo eleitoral, com candidatos e votos registados

## Requisitos funcionais testados

1. Um eleitor pode votar apenas uma vez por eleição.
2. Um voto só é válido se o eleitor tiver idade igual ou superior à idade mínima, o candidato estiver registado, e o eleitor ainda não tiver votado.
3. A contagem de votos por candidato deve refletir corretamente os votos válidos.
4. O vencedor é o candidato com mais votos; em caso de empate, deve devolver `null`.
5. A idade mínima para votar e ser candidato deve ser respeitada em todas as verificações.
6. Deve ser possível obter a lista de todos os votos e candidatos registados.

## Estrutura dos testes

| Ficheiro | Cobertura |
|---|---|
| `EleitorTest.java` | Getters, valores diferentes, ausência de validação na idade |
| `CandidatoTest.java` | Getters, valores diferentes, ausência de validação na idade |
| `VotoTest.java` | Getters, integridade de referências (não faz cópias) |
| `EleicaoTest.java` | Os 6 requisitos funcionais, incluindo casos positivos, negativos e de fronteira |

## Como correr os testes

No IntelliJ: botão direito na pasta `src/test/java` → **Run 'Tests in java'**.

Pelo terminal:
```
./gradlew test
```
Relatório detalhado em `build/reports/tests/test/index.html`.

As classes de produção (`Eleitor`, `Candidato`, `Voto`, `Eleicao`) não foram alteradas. O objetivo deste trabalho foi detetar e documentar, através de testes automatizados, onde e porquê a implementação não cumpre a especificação.
