package br.com.aplicacoesBoilerplate.core;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Propriedades raiz do boilerplate. Permite feature-flag por modulo.
 */
@ConfigurationProperties(prefix = "boilerplate")
public class CBoilerplateProperties {

    private boolean enabled = true;

    public boolean isEnabled() { return enabled; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }
}
