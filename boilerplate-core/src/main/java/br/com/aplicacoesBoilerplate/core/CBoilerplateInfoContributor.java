package br.com.aplicacoesBoilerplate.core;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

/**
 * Contribui com versao do package em /actuator/info.
 * Le Implementation-Version do MANIFEST gerado pelo maven.
 */
@Component
public class CBoilerplateInfoContributor implements InfoContributor {

    @Override
    public void contribute(Info.Builder builder) {
        String version = getClass().getPackage() != null ? getClass().getPackage().getImplementationVersion() : null;
        if (version == null) {
            version = "0.0.1";
        }
        builder.withDetail("boilerplate", java.util.Map.of(
                "version", version,
                "artifact", "boilerplate-core"
        ));
    }
}
