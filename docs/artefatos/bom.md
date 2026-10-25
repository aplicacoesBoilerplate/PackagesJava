# boilerplate-bom

<ProjectVersion />

Bill of Materials para alinhar as versões de boilerplate-core e boilerplate-starter.

## Importação

Dentro de dependencyManagement/dependencies, use:

<MavenArtifact artifact="boilerplate-bom" kind="bom" />

Depois declare core ou starter nas dependencies sem repetir sua versão. Importar um BOM gerencia versões; não adiciona automaticamente os artefatos ao classpath.

## Conteúdo atual

O BOM é um POM sem classes e usa revision para apontar para os módulos da mesma versão conjunta.

[Core](./core) · [Starter](./starter)
