# packages-java-parent

<ProjectVersion />

POM pai e agregador dos módulos BOM, core e starter. Centraliza Java 21, Spring Boot 4.1.1, encoding e configuração dos plugins de compilação, fontes, Javadoc e JAR.

## Uso como parent

<MavenArtifact artifact="packages-java-parent" kind="parent" />

Declare esse elemento como parent do seu POM. Os módulos deste repositório herdam dele; a propriedade revision alinha as dependências internas.

## Escopo atual

É um artefato POM, sem classes de execução. A configuração de distribuição para GitHub Packages existe, mas a etapa atual não executa deploy Maven.

[Instalação e credenciais](../instalacao) · [BOM](./bom)
