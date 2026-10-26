# Homologação Java: revisão independente

**Date:** 2026-10-26
**Verifier:** `/root/consumer_java_verifier`, distinto do autor `/root`.
**Result:** PASS técnico,7/7 critérios verificados após corrigir a descoberta do descritor da fixture. Nenhum gap funcional restante. Revisão/merge em develop/master e publicação Pages permanecem humanos/pendentes.
**Spec:** `.specs/features/consumer-promotion/spec.md`.
**Diff range:** `2431eb20f04a33a75813cf901984084ae9079305..a4b16b1be5209cddee03cf10b17343941f2ef3d6`; gates/sensores iniciais em bfccb5b, consolidação929ab93 e fechamento do descritor/asserção sentinela em a4b16b1. Biblioteca Java inalterada desde bfccb5b; consumidor e CI finais revalidados.

## Escopo e isolamento

Snapshot obtido por git archive do HEAD publicado bfccb5b. A candidata contém origin/develop como ancestral. O diff funcional restringe-se à remoção do BOM UTF-8 do recurso AutoConfiguration.imports; os demais arquivos são fixture/CI/documentação TLC. Nenhum teste existente foi alterado ou removido.

Scratch: `C:/Users/gerso/AppData/Local/Temp/java-consumer-verifier-ee8209bfbcb3468691319a3a860fdf71/`. Os artefatos e fontes compilados pertencem ao snapshot. O Maven usa um repositório próprio no scratch e um mirror file:// somente para leitura do cache candidato do autor; settings/global settings sem credenciais. Não houve deploy, edição no central .github/Go, commit, push, PR, merge ou reexecução remota pelo Verifier.

Antes e depois dos sensores: HEAD real bfccb5b e porcelain vazio. SHA-256 dos JARs reais core/starter em target e no repositório Maven candidato do autor permaneceram idênticos. Fontes scratch restaurados byte a byte, seguidos de novo install e consumidor aprovados.

## Task Completion

| Task | Estado | Evidência |
| --- | --- | --- |
| T1 candidata/contrato | PASS | cb105ff publicado; ancestralidade develop confirmada; spec/tasks sem erros. |
| T2 consumo empacotado | PASS | Reactor quatro módulos/quatro testes; fluxo adicional com quatro contextos HTTP; VitePress e dois sensores aprovados. |
| T3 entrega para develop | PASS dos gates e PR | PR37 aberto para develop em a4b16b1, CI40264958515 e docs40264958835 aprovados; checkpoint final pelo implementador, integração humana pendente. |

## Spec-Anchored Acceptance Criteria

Os paths abaixo pertencem ao consumidor Java. `CPackagedConsumerTest.java` significa `tests/consumer/src/test/java/br/com/aplicacoesBoilerplate/pilot/CPackagedConsumerTest.java`.

| Critério | Resultado definido pela spec | file:line + asserção/outcome | Veredito |
| --- | --- | --- | --- |
| CPRO-01.AC1 padrão | JAR candidato, HTTP200, artifact boilerplate-core, versão real diferente de 9.9.9/unknown | `CPackagedConsumerTest.java:39` exige aplicação9.9.9; `:41` exige `.endsWith(".jar")`; `:42-43` compara digest ao SHA do JAR candidato; `:44-45` compara Implementation-Version à versão, diferente da aplicação e unknown; `:48-50` exige um contributor/properties e `containsEntry("boilerplate", Map.of("artifact", "boilerplate-core", "version", version))`; `:82` exige status200. | PASS |
| CPRO-01.AC2 desativação geral | HTTP200, sem boilerplate/properties/contributor | `CPackagedConsumerTest.java:52-55`: contexto com boilerplate.enabled=false, dois mapas `.isEmpty()` e HTTP `.doesNotContainKey("boilerplate")`; `:82` status200. | PASS |
| CPRO-01.AC3 desativação somente info | HTTP200, properties presente, contributor e payload ausentes | `CPackagedConsumerTest.java:57-60`: `.isEmpty()` no contributor, `.hasSize(1)` nas properties e HTTP sem boilerplate; `:82` status200. | PASS |
| CPRO-01.AC4 customização | Um contributor, identidade preservada e marcador HTTP customizado | `CPackagedConsumerTest.java:62-65`: `.hasSize(1)`, `.isSameAs(CUSTOM)`, `containsEntry("boilerplate", Map.of("source", "custom"))`; `:82` status200. | PASS |
| CPRO-02.AC1 preservação | Features da release, contrato4176883, distribuição false, testes existentes | Diff após bfccb5b preserva fontes da biblioteca; a fixture/CI só receberam o rename e o guard da versão sentinela. `.github/workflows/release.yml:17,21,34,37-38` fixa4176883/false e `.github/workflows/documentacao.yml:15` usa o mesmo SHA publicado. Treze blobs de runtime/script/lockfile/contratos em080df5a e4176883 são idênticos. `.github/workflows/deploy.yml:12` continua if:false. Suíte original1core+3starter preservada. | PASS |
| CPRO-02.AC2 persistir antes de PR | Commits publicados com SHA/gates/limitações | `implementation-log.md:3-9`, README e histórico cb105ff/bfccb5b contêm o relatório/gates/código publicados antes do PR. No fechamento, ls-remote e PR37 confirmaram exatamente a4b16b1be5209cddee03cf10b17343941f2ef3d6 publicado; resultados intermediários foram corrigidos, sem merge remoto. | PASS |
| CPRO-02.AC3 revisão/CI/autoridade | Revisão independente e CI da candidata, promoção humana explícita | Este relatório/sensor, `.github/workflows/ci.yml:25,38` e logs do run40264958515 provam4testes originais+1fluxo HTTP, zero falhas/skips. PR37 é OPEN/draft para develop, mergeable, head a4b16b1; docs40264958835 buildSUCCESS/deploySKIPPED. Master/Pages continuam humanos/pendentes. | PASS |

**Adequação:**7/7 critérios verificados, incluindo a preservação da versão sentinela da fixture durante o versionamento nativo. Os quatro cenários funcionais usam uma aplicação HTTP real, com autoconfiguração descoberta pelo recurso dentro do starter JAR; não importam a classe de autoconfiguração manualmente. Todas as asserções novas pertencem a CPRO-01 ou à vinculação do candidato empacotado, sem testar features futuras.

## Gate Check

Gates iniciais executados sobre o snapshot bfccb5b, com Maven3.9.9 e Java22/target21; a consolidação a4b16b1 está registrada ao final:

1. `mvn -B -ntp -s <settings> -gs <settings> -Dmaven.repo.local=<scratch/repository> -f <snapshot/pom.xml> install`: quatro módulos SUCCESS, quatro testes originais, zero falhas/erros/skips. Log `reactor.log`.
2. `mvn ... -f <snapshot/tests/consumer/pom.xml> -Dboilerplate.version=0.0.1 -Dboilerplate.jar.sha256=<SHA-256 do core JAR recém-construído> verify`: um teste adicional com quatro contextos HTTP, zero falhas/erros/skips. Log `consumer-baseline.log`.
3. `npm ci --prefix <snapshot/docs>` e VitePress real com DOCS_BASE=/PackagesJava/ e DOCS_SOURCE_SHA=bfccb5b...: build aprovado; quatro HTML de artefatos com versão0.0.1 e assets /PackagesJava/. Logs `docs-install.log`/`docs-build.log`. Sem outputs ou node_modules iniciais no snapshot.
4. Após restaurar o sensor BOM: install e mesmo consumidor novamente aprovados, confirmados nos XML Surefire. Logs `reactor-restored.log`/`consumer-restored.log`.
5. `validate_spec`:0 erros/avisos; `validate_tasks`:0 erros e um aviso de granularidade (fixture/CI compõem o mesmo fluxo); `git diff --check`:exit0.

Contagem: baseline quatro testes; após a mudança cinco testes JUnit no conjunto reactor+fixture, sendo o teste novo um fluxo com quatro cenários. Nenhum teste foi apagado, enfraquecido ou ignorado. Warnings de JAR vazio da fixture test-only, propriedades Surefire repetidas e instalação npm/esbuild não foram contados como falhas.

## Discrimination Sensor

| Mutação isolada | Asserção/erro observado | Resultado |
| --- | --- | --- |
| Passar SHA-256 zero como candidato esperado | Asserção de digest no snapshot bfccb5b, `CPackagedConsumerTest.java:40-41` (atual42-43), falhou antes de abrir o contexto; log `sensor-hash.log`, exit1. | KILLED |
| Reintroduzir EFBBBF em AutoConfiguration.imports no snapshot e instalar starter mutante no M2 scratch | Os quatro testes antigos continuam passando, mas o consumidor falha ao ler metadados da classe com U+FEFF no nome; log `sensor-bom.log`, exit1, mensagem específica `Unable to read meta-data for class`. | KILLED |
| Simular deriva da versão da aplicação para0.0.1 no snapshot a4b16b1 | `CPackagedConsumerTest.java:39` rejeitou consumer.application.version=0.0.1, exigindo9.9.9; log `sensor-application-version.log`, exit1. Sem esse override, fluxo4contexts passou em `consumer-a4.log`. | KILLED |

**Resultado:**3/3 KILLED, zero sobreviventes. Removido o BOM mutante restaurando bytes originais; novo build/consumidor passou. O sensor adicional de versão não modifica arquivos e foi seguido do gate positivo no snapshot a4b16b1. Os JARs/M2 candidatos reais do autor não foram alterados.

## Histórico do defeito corrigido e lição

O gate inicial do autor falhou pelo mesmo BOM na baseline de release: recurso de texto com EFBBBF era copiado literalmente para o JAR. O Verifier reproduziu esse comportamento no sensor. `boilerplate-starter/src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports:1` agora contém somente o nome da classe; a correção não altera sua API. A validação anterior que importava a classe diretamente não exercitava o recurso empacotado. Registrar lição candidata sobre testar a autoconfiguração via JAR, com grounding neste histórico e no sensor.

## Pendências e limites

- Os comandos resumidos em spec.md/tasks.md foram corrigidos para incluir `-Dboilerplate.jar.sha256`, como já faziam README/CI. Os gates reais sempre incluíram ambos os parâmetros; nenhuma asserção foi simplificada.
- CPRO-02.AC3 agora tem PR37/CI final comprovados. Este PASS técnico não comprova merge em develop/master ou publicação Pages; o PR foi verificado como draft, e o checkpoint de entrega/revisão é concluído pelo implementador.
- Nenhum gap funcional restante após o fechamento a4b16b1. A decisão de integrar continua com a revisão humana.

## Consolidação T3 no HEAD929ab93

- Reconsulta independente do PR37 confirmou destino develop, head929ab9384419ea80cdbaac8cbf7824a0b185ed16, estado OPEN/draft, mergeable e checks finais aprovados. ls-remote confirmou o mesmo SHA publicado.
- [CI40263567559](https://github.com/aplicacoesBoilerplate/PackagesJava/actions/runs/40263567559): SUCCESS no HEAD final. Logs conferidos: quatro módulos; teste core1/starter3 e consumidor1, zero falhas/erros/skips; os passos de install, hash candidato e consumidor real concluíram com sucesso.
- [Documentação40263579254](https://github.com/aplicacoesBoilerplate/PackagesJava/actions/runs/40263579254): buildSUCCESS no mesmo HEAD, VitePress real; upload/deploySKIPPED em PR conforme o contrato. O link externo de reprodução agora aponta para o repositório, sem tentar resolver ../tests como página VitePress.
- O repin de080df5a para4176883e69cf32fe36d1b893e246bbdc03885cf9 resolve a referência de Actions sem alterar o runtime. A comparação independente das trees Git via API verificou treze blobs: todos os scripts centrais, package.json/package-lock.json e os três contratos documentacao-pages/version-release-please-called/version-release-please-validate têm SHA de blob idêntico nos dois commits. A Release centralv0.1.0 está publicada nesse novo commit, não draft/prerelease. Somente callers dos consumidores foram alterados; nenhuma escrita no central foi realizada.
- Entre bfccb5b e929ab93 mudaram callers, evidências TLC e documentação. Biblioteca Java, recurso corrigido, fixture e CI de consumo não mudaram; não houve repetição desnecessária de compiladores/sensores locais. A nova execução hospedada comprova esses mesmos gates em Java21.
- Validação estrutural final e diff check são executados sobre este relatório. Os arquivos de implementação permanecem somente leitura pelo Verifier; somente este relatório foi atualizado na consolidação.

## Gap complementar resolvido: POM da fixture descoberto pelo versionamento nativo

- **Prioridade histórica:** P1, resolvido em a4b16b1. CPRO-01.AC1 depende de um aplicativo sentinela9.9.9 distinto da biblioteca; o ensaio não deve perder essa distinção a cada release.
- **Causa comprovada:** SDK17.3.0 `strategies/maven.js:32` chama `findFilesByFilenameAndRef('pom.xml', ...)` e aplica `PomXml` a todo resultado. `updaters/java/pom-xml.js:33-43` reescreve project/version quando presente. Portanto tests/consumer/pom.xml é coletado junto com os quatro POMs de artefatos.
- **Ensaio independente somente em memória:** `new PomXml(Version.parse('0.0.2')).updateContent(conteudoRealDaFixture)` removeu9.9.9 e colocou0.0.2; o parent Spring Boot permaneceu4.1.1 porque project/version existe. Não houve escrita de fonte ou chamada GitHub pelo ensaio.
- **Correção mínima proposta:** renomear o descritor da fixture para consumer-pom.xml e atualizar os caminhos `-f` na CI/README/spec/tasks. O Maven aceita o descritor explícito; a descoberta nativa por nome exato deixa de coletá-lo. Não alterar o central ou o algoritmo nativo.
- **Done when:** o descritor deixa de ser chamado pom.xml, todas as invocações apontam para o novo arquivo, o ensaio nativo preserva fixture9.9.9/parent4.1.1 e o consumidor/CI correspondente passam. Os testes HTTP e asserções devem permanecer idênticos.

## Fechamento complementar no HEAD a4b16b1

- `tests/consumer/consumer-pom.xml:13` mantém9.9.9 e `:54` fornece consumer.application.version=${project.version} ao Surefire. `CPackagedConsumerTest.java:39,44-45` agora exige9.9.9 e versão da biblioteca diferente da versão efetiva da aplicação. Nenhuma asserção HTTP anterior foi removida ou relaxada.
- Confirmação independente por `git ls-files` e basename: existem exatamente quatro arquivos chamados pom.xml, somente root/BOM/core/starter. O descritor anterior tests/consumer/pom.xml não existe. CI/README/spec/tasks usam `-f tests/consumer/consumer-pom.xml`.
- Ensaio da descoberta nativa, sem rede: BranchFileCache de @google-automations/git-file-utils comparou basename exato e retornou cinco POMs antes do rename, quatro depois, excluindo consumer-pom.xml. O teste em memória do PomXml já havia demonstrado o risco de reescrever9.9.9 para0.0.2. Essa conclusão usa o motor/biblioteca reais com transporte de arquivos simulado; não é uma publicação GitHub.
- Novo snapshot git archive em `<scratch>/source-a4`; fixture real executada com o M2 isolado já aprovado. O consumidor passou com um fluxo/quatro contextos, zero falhas/erros/skips em9s, log `consumer-a4.log`. O sensor de deriva da aplicação foi rejeitado em `:39` e a restauração da entrada passou.
- [CI40264958515](https://github.com/aplicacoesBoilerplate/PackagesJava/actions/runs/40264958515) SUCCESS no SHA a4b16b1: logs confirmam core1/starter3/consumidor1, zero falhas/erros/skips. [Docs40264958835](https://github.com/aplicacoesBoilerplate/PackagesJava/actions/runs/40264958835) SUCCESS no mesmo SHA, deploySKIPPED. PR37/head/ls-remote confirmados novamente.
- A biblioteca não mudou desde bfccb5b; por isso não houve repetição local dos quatro módulos ou do build documental nesta rodada. CI hospedada final executou ambos; localmente o Verifier repetiu somente a fixture alterada e seu sensor. Não houve writes de implementação, merge ou publicação pelo Verifier.
- Veredito final: PASS técnico,7/7ACs e3/3 sensores. L-002 permanece candidata, como registro do gap real encontrado e resolvido, sem recorrência artificial. O fechamento exige só persistência do relatório/status pelo implementador e posterior decisão humana de integração.
