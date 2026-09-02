package br.com.aplicacoesBoilerplate.starter;

import br.com.aplicacoesBoilerplate.core.CBoilerplateInfoContributor;
import br.com.aplicacoesBoilerplate.core.CBoilerplateProperties;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * AutoConfiguration do starter. So ativa quando boilerplate.enabled=true (default).
 */
@AutoConfiguration
@EnableConfigurationProperties(CBoilerplateProperties.class)
@ConditionalOnProperty(prefix = "boilerplate", name = "enabled", havingValue = "true", matchIfMissing = true)
public class CBoilerplateAutoConfiguration {

    @Bean
    @ConditionalOnProperty(prefix = "boilerplate.info", name = "enabled", havingValue = "true", matchIfMissing = true)
    public InfoContributor boilerplateInfoContributor() {
        return new CBoilerplateInfoContributor();
    }
}
