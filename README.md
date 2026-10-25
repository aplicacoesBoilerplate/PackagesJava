# PackagesJava

Repositorio de packages utilitarios Java. A versão do código é apresentada na documentação a partir do manifest nativo.

A documentação VitePress dos quatro artefatos está em [docs/](docs/index.md). Para compilar: npm ci --prefix docs e npm run docs:build --prefix docs. A publicação usa o workflow documental central após integração na master e configuração de Pages.

## Estrutura multi-modulo
- `boilerplate-bom` - Bill of Materials
- `boilerplate-core` - core + Actuator InfoContributor
- `boilerplate-starter` - AutoConfiguration com ConditionalOnProperty

## Uso

### settings.xml (~/.m2/settings.xml)
```xml
<server>
  <id>github-boilerplate</id>
  <username>SEU_USUARIO</username>
  <password>SEU_PAT_com_read:packages</password>
</server>
```

### pom.xml consumidor
```xml
<repositories>
  <repository><id>github-boilerplate</id><url>https://maven.pkg.github.com/aplicacoesBoilerplate/PackagesJava</url></repository>
</repositories>
<dependency>
  <groupId>br.com.aplicacoesBoilerplate</groupId>
  <artifactId>boilerplate-starter</artifactId>
  <version>0.0.1</version>
</dependency>
```

### DX com CLI interna
```bash
boilerplate auth login   # le gh auth token e escreve settings.xml
boilerplate add java boilerplate-starter@0.0.1
boilerplate doctor
```

## Git Flow
- defaultBranch: master
- feature/* -> develop -> release/* -> master; hotfix/* também pode seguir para master.
- O versionamento usa o Release Please centralizado: [fluxo, configuração e teste da etapa 1](docs/versioning.md). Tag/GitHub Release são automáticas após o PR técnico; distribuição Maven fica para a etapa 2.
