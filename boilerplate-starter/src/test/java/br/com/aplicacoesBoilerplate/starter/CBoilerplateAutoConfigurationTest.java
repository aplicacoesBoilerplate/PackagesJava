package br.com.aplicacoesBoilerplate.starter;

import br.com.aplicacoesBoilerplate.core.CBoilerplateInfoContributor;
import br.com.aplicacoesBoilerplate.core.CBoilerplateProperties;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

class CBoilerplateAutoConfigurationTest {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(CBoilerplateAutoConfiguration.class));

    @Test
    void shouldCreateInfoContributorByDefault() {
        contextRunner.run(pContext -> {
            assertThat(pContext).hasSingleBean(CBoilerplateProperties.class);
            assertThat(pContext).hasSingleBean(CBoilerplateInfoContributor.class);
        });
    }

    @Test
    void shouldDisableAutoConfiguration() {
        contextRunner
                .withPropertyValues("boilerplate.enabled=false")
                .run(pContext -> {
                    assertThat(pContext).doesNotHaveBean(CBoilerplateProperties.class);
                    assertThat(pContext).doesNotHaveBean(CBoilerplateInfoContributor.class);
                });
    }

    @Test
    void shouldDisableOnlyInfoContributor() {
        contextRunner
                .withPropertyValues("boilerplate.info.enabled=false")
                .run(pContext -> {
                    assertThat(pContext).hasSingleBean(CBoilerplateProperties.class);
                    assertThat(pContext).doesNotHaveBean(CBoilerplateInfoContributor.class);
                });
    }
}
