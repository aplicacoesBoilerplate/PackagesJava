import { readFileSync } from 'node:fs';
import { execFileSync } from 'node:child_process';
import { fileURLToPath } from 'node:url';

export interface IJavaDocumentation {
  version: string;
  sha: string;
}
declare const data: IJavaDocumentation;
export { data };

export default {
  watch: ['../.release-please-manifest.json', './package.json'],
  load(): IJavaDocumentation {
    const manifest = JSON.parse(readFileSync(new URL('../.release-please-manifest.json', import.meta.url), 'utf8'));
    const docs = JSON.parse(readFileSync(new URL('./package.json', import.meta.url), 'utf8'));
    if (typeof manifest['.'] !== 'string' || docs.version !== manifest['.']) {
      throw new Error('A versão documental deve acompanhar o manifest Maven do mesmo checkout');
    }
    return {
      version: manifest['.'],
      sha: process.env.DOCS_SOURCE_SHA || execFileSync('git', ['rev-parse', 'HEAD'], {
        cwd: fileURLToPath(new URL('../', import.meta.url)), encoding: 'utf8',
      }).trim(),
    };
  },
};
