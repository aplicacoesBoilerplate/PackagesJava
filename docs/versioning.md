# Versionamento: etapa 1

Caller local: .github/workflows/release.yml. Ele chama o workflow central e seus scripts na mesma revisão imutável d79c8c621a5fc85d0327fac4aafe13d2cfdb9cd0, depois de mvn verify do commit integrado em master. Apenas o job de release recebe escrita; não herda secrets de registry.

Entrega funcional (develop ou hotfix para master) declara a versão por milestone SemVer ou footer Release-As conforme o contrato central atual. O Release Please prepara um PR técnico com essa versão. Revisão, CI e merge desse PR geram tag v0.0.2 e GitHub Release automaticamente, sem outra milestone. Fechar sem merge não publica.

Parent, BOM, core e starter mantêm uma versão conjunta. A estratégia Maven atualiza os POMs e seus pais; o updater XML nativo sincroniza revision, preservando referências internas. skip-snapshot evita um terceiro PR de snapshot. O config não contém release-as permanente. Os extra-files nativos sincronizam docs/package.json e as duas versões do lockfile documental, além de revision. O manifest começa na versão atual 0.0.1 e bootstrap-sha limita a migração ao histórico posterior à base real desta integração.

publish-artifacts=false impede mvn deploy. O antigo deploy.yml perdeu o gatilho de tag, não tem packages:write e tem if:false, inclusive para execução manual. distributionManagement permanece preservado para a etapa 2. Nenhuma publicação de pacote integra esta entrega.

## Verificação e progresso T4

- Integração preparada na branch codex/release-please-stage1 a partir de release/v0.0.1; não promove código funcional para master.
- Gate aprovado: mvn -B verify nos quatro módulos (quatro testes, zero skips/falhas), sem deploy; contrato YAML/config/deploy desligado aprovado. Contrato nativo Maven/revision/parents e tag/SHA também coberto pela suíte central com o motor real.
- O ciclo hospedado do consumidor permanece pendente da revisão e dos merges humanos. Depois de levar esta integração até master pelo Git Flow do projeto, usar uma entrega fix:/feat: com milestone, inspecionar o PR técnico e conferir tag/Release no SHA do segundo merge.
- T4: implementação e gates locais concluídos; commit de integração publicado antes da tarefa seguinte. PR de integração não encerra issues funcionais; ciclo real continua pendente.

[Contrato central](https://github.com/aplicacoesBoilerplate/.github/blob/d79c8c621a5fc85d0327fac4aafe13d2cfdb9cd0/docs/pipelines/versionamento-release-please.md).

## Iteração documental T2

O caller usa os inputs atuais config-file, manifest-file e project-path raiz; o check somente leitura de disponibilidade da versão atua nos eventos de PR para master. A CI Maven continua antecedendo o writer. O site em docs/ apresenta os quatro artefatos com versão do mesmo checkout, sem prometer publicação no registry. O caller documentacao.yml usa o mesmo SHA central; PR faz build, master faz deploy quando Pages estiver habilitado.

Gates locais desta iteração: VitePress1.6.4 com quatro páginas de artefatos, versões/coordenadas/base conferidas no HTML e mvn verify dos quatro módulos/quatro testes, sem falhas/skips. A publicação online ainda depende de configuração Pages e dos merges humanos. Nenhum fonte Java foi alterado.

O buildEnd do site verifica as quatro páginas renderizadas e suas coordenadas Maven. O ensaio com o SDK oficial nos arquivos reais confirmou POMs/revision e as três versões JSON em0.0.2; o loader rejeitou manifest desatualizado e JSON adulterado. T2 inclui esses gates e o status no mesmo commit.
