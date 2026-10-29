<script setup lang="ts">
import { data } from '../../packages.data.ts';
</script>

<template>
  <div class="custom-block info">
    <p><strong>Versão do código documentado: {{ data.version }}</strong></p>
    <p>Commit: <code>{{ data.sha.slice(0, 7) }}</code>.</p>
    <p>Esta informação identifica o checkout; não comprova disponibilidade no registry Maven.</p>
  </div>
</template>
