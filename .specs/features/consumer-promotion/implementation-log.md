# Homologação Java

- T1 publicada em cb105ff826c70727e57e61db53d46c7c22066939. Candidata parte de release2431eb2 e incorpora develop sem alteração de tree funcional; originais sujos preservados.
- T2 usa Maven3.9.9/Java22 com target21. Settings temporários vazios de credenciais e mirror exclusivo Maven Central; repositório local isolado em Temp/java-packaged-consumer-fdaa98bbdc2449aeae87f6e536cee4cc/repository. Nenhum settings pessoal foi lido nem deploy executado.
- Reactor install inicial: quatro módulos aprovados e quatro testes preservados. O primeiro consumidor real falhou ao iniciar: AutoConfiguration.imports começou com EFBBBF, preservado no JAR. Spring tentou carregar uma classe com U+FEFF no nome. Os testes anteriores importavam a classe diretamente e não exercitavam esse recurso empacotado.
- Correção mínima: remover somente o BOM UTF-8 desse recurso. O mesmo teste, sem relaxar asserções, passou após reconstruir/reinstalar os candidatos. Logs reactor-fixed.log e consumer-fixed.log preservados no scratch.
- Gate: quatro módulos/quatro testes originais aprovados; um teste funcional adicional passou com quatro contextos HTTP reais. Origem JAR e hash do candidato conferidos, versão0.0.1, padrão HTTP200/artifact/versão; desativação geral sem properties/contributor; desativação somente info preservando properties; custom bean único e marcador no HTTP.
- CI passa a instalar candidatos localmente, calcular hash/versão do JAR e executar a fixture externa. publish-artifacts=false e deploy if:false preservados. A distribuição continua fora do escopo.
- T3 ainda exige revisão independente/sensor, CI hospedada da candidata e PR para develop. Nenhum merge remoto ou publicação Pages foi realizado.

## T3 em andamento

- PR37 draft aberto para develop. CI40262515129 aprovou o reactor e o consumidor real no runner GitHub.
- O workflow documental40262515353 foi rejeitado antes de criar jobs: o Actions não encontrou documentacao-pages.yml na referência080df5a. A antiga branch central foi removida após a integração; não se presumiu que o SHA continuaria utilizável por Actions.
- Repin para4176883e69cf32fe36d1b893e246bbdc03885cf9, commit da release central publicada v0.1.0, somente nos callers do consumidor e guia. Comparação via API e git show confirmou identidade dos três workflows, CManifestRelease/CReleaseVersionValidator/CReleaseTagGuard e lockfile de runtime com080df5a. Nenhum arquivo central foi modificado.
- Comandos resumidos TLC agora incluem o hash obrigatório do JAR, como README/CI já faziam. Revisão independente aprovou gates e dois sensores (hash errado/BOM), com restauração e preservação dos artefatos reais.
- Checkpoint de integração aguarda a nova CI e consolidação do relatório antes de apresentar o PR como pronto.
- O repin resolveu a localização do called. CI40263013892 passou; docs40263014432 encontrou link relativo recém-adicionado para fora da raiz docs/. Corrigido para URL do repositório de destino e exigido novo build VitePress antes do próximo push, sem desabilitar verificação de links.
