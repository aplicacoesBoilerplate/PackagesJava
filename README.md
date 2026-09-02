# PackagesJava

Repositorio de packages utilitarios Java - `0.0.1-beta`.

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
- feature/* -> develop -> release/* -> master -> tag v0.0.1 -> deploy
