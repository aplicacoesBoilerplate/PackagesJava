import DefaultTheme from 'vitepress/theme';
import ProjectVersion from './ProjectVersion.vue';
import MavenArtifact from './MavenArtifact.vue';

export default {
  extends: DefaultTheme,
  enhanceApp({ app }) {
    app.component('ProjectVersion', ProjectVersion);
    app.component('MavenArtifact', MavenArtifact);
  },
};
