import { readFileSync } from 'node:fs';
import { join } from 'node:path';
import assert from 'node:assert/strict';
import { defineConfig } from 'vitepress';

const metadata = JSON.parse(readFileSync(new URL('../package.json', import.meta.url), 'utf8'));
export default defineConfig({
  lang: 'pt-BR',
  title: 'PackagesJava',
  description: 'Parent, BOM, core e starter Java da organização Aplicações Boilerplate.',
  base: process.env.DOCS_BASE || '/PackagesJava/',
  buildEnd(site) {
    for (const artifact of ['parent', 'bom', 'core', 'starter']) {
      const html = readFileSync(join(site.outDir, 'artefatos', artifact + '.html'), 'utf8');
      const text = html.replace(/<[^>]*>/g, '');
      assert.ok(text.includes('Versão do código documentado: ' + metadata.version), 'Versão renderizada ausente em ' + artifact);
      assert.ok(html.includes('&lt;version&gt;' + metadata.version + '&lt;/version&gt;'), 'Coordenadas Maven divergentes em ' + artifact);
    }
  },
  themeConfig: {
    nav: [
      { text: 'Versão do código ' + metadata.version, link: '/versionamento' },
      { text: 'Artefatos', link: '/artefatos/parent' },
      { text: 'GitHub', link: 'https://github.com/aplicacoesBoilerplate/PackagesJava' },
    ],
    sidebar: [
      { text: 'Começar', items: [
        { text: 'Visão geral', link: '/' },
        { text: 'Instalação', link: '/instalacao' },
        { text: 'Versão e publicação', link: '/versionamento' },
      ] },
      { text: 'Artefatos', items: [
        { text: 'Parent', link: '/artefatos/parent' },
        { text: 'BOM', link: '/artefatos/bom' },
        { text: 'Core', link: '/artefatos/core' },
        { text: 'Starter', link: '/artefatos/starter' },
      ] },
    ],
    search: { provider: 'local' },
  },
});
