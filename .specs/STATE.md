# STATE

## Decisions

Aplicar TLC ao piloto consumidor; manter implementação inline, artefatos reais, distribuição desligada e merges humanos. Central .github concluído e fora do escopo.

## Handoff

- **Feature:** consumer-promotion/spec.md.
- **Phase / Task:** Execute / T2 aprovada localmente; checkpoint antes de T3.
- **Baseline:** release 2431eb20f04a33a75813cf901984084ae9079305, candidata codex/consumer-homologation-v0.0.1 reconciliada com origin/develop.
- **Completed:** refs conferidas, worktree limpo reaproveitado, merge local sem conflitos; fontes funcionais preservados.
- **In-progress:** T3: PR37 draft; CI929 e docs aprovados após repin4176883 e correção de link. Revisão encontrou que Maven nativo versionaria a fixture; descritor renomeado consumer-pom.xml, com asserção de aplicação9.9.9 e novo gate HTTP aprovado.
- **Next step:** publicar isolamento da fixture, conferir CI e revalidação independente final; apresentar PR37 para revisão/merge humano em develop. Central/Go fora do escopo.
- **Blockers:** nenhum para executar testes; integração remota e master permanecem humanos.
