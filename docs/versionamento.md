# Versão e publicação

<ProjectVersion />

O Release Please centralizado é o motor único. Parent, BOM, core e starter compartilham uma versão; os POMs, a propriedade revision, o manifest nativo e os JSONs de versão em docs/ são atualizados pelo mesmo PR técnico.

O caller deste repositório usa config-file, manifest-file e project-path raiz com o mesmo SHA revisado dos scripts centrais. O PR funcional para master declara a versão por milestone ou Release-As, conforme o contrato central. O PR técnico posterior publica tag e GitHub Release após revisão, CI e merge humano.

publish-artifacts permanece false: tag/Release e distribuição Maven são etapas distintas. O site não consulta um número independente mantido manualmente e não usa jgitver.

## Publicação do site

O caller documental usa o workflow central de Pages. PRs compilam sem deploy. Push/manual na master publica quando Pages estiver configurado como GitHub Actions e o environment github-pages permitir a implantação. A integração continua destinada à release/v0.0.1 e não promove as features para master.

[Guia da integração](./versioning).
