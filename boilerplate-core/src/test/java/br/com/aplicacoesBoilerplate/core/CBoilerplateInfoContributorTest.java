package br.com.aplicacoesBoilerplate.core;

import org.junit.jupiter.api.Test;
import org.springframework.boot.actuate.info.Info;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class CBoilerplateInfoContributorTest {

    @Test
    void contributeShouldExposeArtifactAndVersion() {
        CBoilerplateInfoContributor contributor = new CBoilerplateInfoContributor("1.2.3");
        Info.Builder builder = new Info.Builder();

        contributor.contribute(builder);

        assertThat(builder.build().getDetails())
                .containsEntry("boilerplate", Map.of(
                        "artifact", "boilerplate-core",
                        "version", "1.2.3"
                ));
    }
}
