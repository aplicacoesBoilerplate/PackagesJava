<script setup lang="ts">
import { computed } from 'vue';
import { data } from '../../packages.data.ts';
const props = defineProps<{ artifact: string; kind?: 'dependency' | 'bom' | 'parent' }>();
const snippet = computed(() => {
  const tag = props.kind === 'parent' ? 'parent' : 'dependency';
  const lines = [
    '<' + tag + '>',
    '  <groupId>br.com.aplicacoesBoilerplate</groupId>',
    '  <artifactId>' + props.artifact + '</artifactId>',
    '  <version>' + data.version + '</version>',
  ];
  if (props.kind === 'bom') lines.push('  <type>pom</type>', '  <scope>import</scope>');
  lines.push('</' + tag + '>');
  return lines.join('\n');
});
</script>

<template><pre><code>{{ snippet }}</code></pre></template>
