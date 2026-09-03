package br.com.aplicacoesBoilerplate.core;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;

import java.util.Map;

/**
 * Contribui com versao do package em /actuator/info.
 * Le Implementation-Version do MANIFEST gerado pelo maven.
 */
public class CBoilerplateInfoContributor implements InfoContributor {

    private static final String ARTIFACT = "boilerplate-core";
    private static final String UNKNOWN_VERSION = "unknown";

    private final String version;

    /**
     * Cria o contributor com a versao registrada no manifesto do package.
     */
    public CBoilerplateInfoContributor() {
        this(resolveImplementationVersion());
    }

    CBoilerplateInfoContributor(String pVersion) {
        version = pVersion;
    }

    @Override
    public void contribute(Info.Builder pBuilder) {
        pBuilder.withDetail("boilerplate", Map.of(
                "version", version,
                "artifact", ARTIFACT
        ));
    }

    private static String resolveImplementationVersion() {
        Package packageMetadata = CBoilerplateInfoContributor.class.getPackage();
        String implementationVersion = packageMetadata != null ? packageMetadata.getImplementationVersion() : null;
        return implementationVersion == null || implementationVersion.isBlank()
                ? UNKNOWN_VERSION
                : implementationVersion;
    }
}
