# boilerplate-core

<ProjectVersion />

Biblioteca com propriedades de configuração e contribuição da versão para o Actuator.

## Dependência

<MavenArtifact artifact="boilerplate-core" />

## APIs existentes

- CBoilerplateProperties: propriedades sob o prefixo boilerplate; enabled é true por padrão.
- CBoilerplateInfoContributor: implementa InfoContributor e acrescenta boilerplate.version e boilerplate.artifact à informação do Actuator.

O contributor lê Implementation-Version do manifesto do JAR de boilerplate-core. Quando esse metadado não está disponível, informa unknown; ele não assume que a versão da aplicação consumidora seja a versão da biblioteca.

Para registro automático dos beans, utilize o [starter](./starter). Este módulo ainda não possui as APIs de respostas, exceções e criptografia mencionadas em descrições antigas do projeto.

[Instalação](../instalacao)
