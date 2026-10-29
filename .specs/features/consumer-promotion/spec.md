# Homologação do consumidor Java e promoção para develop

**Status:** homologação técnica e revisão independente aprovadas; candidata publicada para revisão/integração humana em develop. Master e Pages permanecem pendentes.

## Problem Statement
A release candidata já contém a infraestrutura centralizada e documentação, mas a CI não demonstra todo o uso dos artefatos por aplicações externas. Homologar antes de promover para master.

## Goals
- [x] Reconciliar develop com a release 2431eb20f04a33a75813cf901984084ae9079305 sem alterar o produto ou reativar distribuição.
- [x] Provar o uso de artefatos empacotados e registrar evidência reproduzível.
- [x] Entregar candidato/PR para develop, com CI/revisão e pendências humanas explícitas.

## Out of Scope
| Feature | Reason |
| --- | --- |
| Central .github, Go e features pendentes | Usuário restringiu a iteração aos consumidores Java/Vue existentes. |
| Publicação npm/Maven e mudanças de produto não necessárias ao piloto | Distribuição permanece false; não coletar funcionalidades futuras. |
| Merge unilateral em develop/master | Preservar revisão/merges humanos combinados; entregar resultado concreto antes da integração. |

## Assumptions & Open Questions
| Decision | Default | Rationale | Confirmed? |
| --- | --- | --- | --- |
| Candidata | codex/consumer-homologation-v0.0.1 baseada na release e reconciliada com develop | Isola evidências/testes sem mexer em originais sujos ou features | Escolha de implementação |
| Homologação | Artefatos locais reais, sem registry/deploy | Verifica consumo sem publicar pacotes | Autorizado no fluxo aprovado |
| Corte | Baseline atual da release | PRs de features futuras ficam fora | Confirmado pelo usuário |

**Open questions:** none para executar os gates; aceite visual/produto e merges permanecem humanos.

## User Stories

### P1: Consumo real dos artefatos
**User Story:** Como mantenedor, quero verificar o pacote instalado em uma aplicação antes de promover a release.
**Acceptance Criteria:**
1. WHEN o consumidor iniciar com os JARs candidatos THEN /actuator/info SHALL responder HTTP200 com boilerplate.artifact=boilerplate-core e a versão da biblioteca, diferente de 9.9.9 e unknown; a classe SHALL vir de um .jar.
2. WHEN boilerplate.enabled=false THEN o consumidor SHALL responder HTTP200 sem boilerplate, sem properties e sem contributor.
3. WHEN boilerplate.info.enabled=false THEN o consumidor SHALL responder HTTP200 sem boilerplate, preservando properties e removendo somente o contributor.
4. WHEN o consumidor declarar um bean customizado THEN ele SHALL manter exatamente um contributor, a identidade customizada e seu marcador no HTTP, sem bean automático duplicado.
**Independent Test:** mvn -B install; mvn -B -f tests/consumer/consumer-pom.xml -Dboilerplate.version=<versão candidata> -Dboilerplate.jar.sha256=<hash do JAR candidato> verify; build VitePress.

### P1: Promoção verificável
**User Story:** Como mantenedor, quero revisar uma candidata fiel à release e ao destino.
**Acceptance Criteria:**
1. WHEN a candidata for preparada THEN ela SHALL preservar as features da release e o contrato central fixado em4176883, publish-artifacts=false e todos os testes existentes.
2. WHEN os gates terminarem THEN relatório e código SHALL ser commitados/publicados antes de abrir o PR para develop, identificando SHA, resultados e limitações.
3. WHEN a entrega for apresentada THEN ela SHALL conter revisão independente e CI da candidata; IF a integração depender de revisão humana THEN ela SHALL registrar esse estado sem declarar master ou Pages publicados.
**Independent Test:** Comparação Git, gate completo, sensor isolado e revisão independente.

## Edge Cases
- IF o ensaio usar fontes/aliases no lugar dos artefatos empacotados THEN ele SHALL falhar.
- IF qualquer gate falhar THEN a candidata SHALL permanecer sem promoção.
- IF surgirem defects fora do piloto THEN a entrega SHALL registrar o impedimento sem desenvolver features não aprovadas.

## Requirement Traceability
| Requirement ID | Story | Status |
| --- | --- | --- |
| CPRO-01 | Consumo real | Validado independentemente, com gates e sensores registrados no relatório |
| CPRO-02 | Promoção verificável | PR37 para develop, CI e revisão aprovadas; integração humana pendente |

## Success Criteria
- [x] Artefatos instalados e cenários funcionais aprovados.
- [x] Gates preservados, evidências e sensor independentes aprovados.
- [x] Candidata publicada e PR para develop entregue; master/deploy explicitamente pendentes da integração humana.
