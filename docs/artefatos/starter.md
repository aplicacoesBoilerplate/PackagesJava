# boilerplate-starter

<ProjectVersion />

Starter Spring Boot com dependência transitiva de boilerplate-core. A autoconfiguração CBoilerplateAutoConfiguration está registrada pelo mecanismo de imports do Spring Boot.

## Dependência

<MavenArtifact artifact="boilerplate-starter" />

## Configuração

~~~yaml
boilerplate:
  enabled: true
  info:
    enabled: true
~~~

As duas opções são habilitadas por padrão. boilerplate.enabled=false desativa toda a autoconfiguração; boilerplate.info.enabled=false desativa somente o contributor de versão. Um CBoilerplateInfoContributor já fornecido pela aplicação é preservado por ConditionalOnMissingBean.

Para consultar /actuator/info, a aplicação consumidora precisa configurar a exposição desse endpoint conforme sua política de segurança; o starter não o torna público.

[Core](./core) · [Instalação](../instalacao)
