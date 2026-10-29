# Consumidor empacotado do piloto

Este projeto fica fora do reactor. Ele resolve parent/BOM/core/starter instalados localmente e abre quatro aplicações HTTP reais, sem importar a autoconfiguração manualmente. A versão da aplicação de ensaio é 9.9.9; a versão informada deve ser a do JAR da biblioteca.

Após `mvn -B install` na raiz, executar:

```powershell
$version = (Get-Content -Raw .release-please-manifest.json | ConvertFrom-Json).'.'
$hash = (Get-FileHash "boilerplate-core/target/boilerplate-core-$version.jar" -Algorithm SHA256).Hash.ToLowerInvariant()
mvn -B -f tests/consumer/consumer-pom.xml "-Dboilerplate.version=$version" "-Dboilerplate.jar.sha256=$hash" verify
```

O SHA-256 vincula o JAR resolvido ao candidato recém-construído e impede aceitar uma cópia antiga do cache. O teste exige origem `.jar`, versão de manifesto correta, HTTP200 e os campos do payload, os dois controles de desativação e a identidade do bean customizado sem duplicação.

O descritor se chama consumer-pom.xml de propósito. O Release Please Maven procura arquivos chamados exatamente pom.xml e altera project/version; assim a versão9.9.9 desta aplicação de ensaio permanece fixa enquanto as bibliotecas evoluem. O teste também exige explicitamente essa versão da aplicação e compara a versão do JAR contra ela.

`install` grava somente o repositório Maven local. Não executa `deploy`, não publica pacotes nem precisa de credenciais GitHub. A CI realiza os mesmos dois builds, preservando os quatro testes existentes e acrescentando um único fluxo funcional com quatro cenários.
