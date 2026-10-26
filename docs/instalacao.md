# Instalação

<ProjectVersion />

Os artefatos usam o groupId br.com.aplicacoesBoilerplate. Para consumir versões efetivamente publicadas no GitHub Packages, configure o repositório Maven:

~~~xml
<repositories>
  <repository>
    <id>github-boilerplate</id>
    <url>https://maven.pkg.github.com/aplicacoesBoilerplate/PackagesJava</url>
  </repository>
</repositories>
~~~

Credenciais devem ficar no settings.xml do usuário ou em secrets da CI, com leitura de packages. Não salve tokens no projeto. A versão exibida neste site é a versão do código documentado, não uma confirmação de publicação no registry.

Você pode herdar o [parent](./artefatos/parent) ou importar o [BOM](./artefatos/bom) em dependencyManagement. Para autoconfiguração, adicione o [starter](./artefatos/starter).

## Verificação local

~~~text
mvn -B verify
npm ci --prefix docs
npm run docs:build --prefix docs
~~~

O build do site verifica a igualdade entre a versão documental e o manifest Maven do checkout.
