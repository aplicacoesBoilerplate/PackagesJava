package br.com.aplicacoesBoilerplate.pilot;

import br.com.aplicacoesBoilerplate.core.CBoilerplateInfoContributor;
import br.com.aplicacoesBoilerplate.core.CBoilerplateProperties;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.util.HexFormat;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class CPackagedConsumerTest {
    private static final CBoilerplateInfoContributor CUSTOM = new CBoilerplateInfoContributor() {
        @Override
        public void contribute(Info.Builder pBuilder) {
            pBuilder.withDetail("boilerplate", Map.of("source", "custom"));
        }
    };

    @Test
    void shouldConsumeCandidateJarAndHonorStarterControlsOverHttp() throws Exception {
        String version = System.getProperty("boilerplate.version");
        Path jar = Path.of(CBoilerplateInfoContributor.class.getProtectionDomain().getCodeSource().getLocation().toURI());
        assertThat(jar.toString()).endsWith(".jar");
        assertThat(HexFormat.of().formatHex(MessageDigest.getInstance("SHA-256").digest(Files.readAllBytes(jar))))
                .isEqualTo(System.getProperty("boilerplate.jar.sha256"));
        assertThat(CBoilerplateInfoContributor.class.getPackage().getImplementationVersion())
                .isEqualTo(version).isNotEqualTo("9.9.9").isNotEqualTo("unknown");

        try (ConfigurableApplicationContext context = start(false)) {
            assertThat(context.getBeansOfType(CBoilerplateInfoContributor.class)).hasSize(1);
            assertThat(context.getBeansOfType(CBoilerplateProperties.class)).hasSize(1);
            assertThat(info(context)).containsEntry("boilerplate", Map.of("artifact", "boilerplate-core", "version", version));
        }
        try (ConfigurableApplicationContext context = start(false, "--boilerplate.enabled=false")) {
            assertThat(context.getBeansOfType(CBoilerplateInfoContributor.class)).isEmpty();
            assertThat(context.getBeansOfType(CBoilerplateProperties.class)).isEmpty();
            assertThat(info(context)).doesNotContainKey("boilerplate");
        }
        try (ConfigurableApplicationContext context = start(false, "--boilerplate.info.enabled=false")) {
            assertThat(context.getBeansOfType(CBoilerplateInfoContributor.class)).isEmpty();
            assertThat(context.getBeansOfType(CBoilerplateProperties.class)).hasSize(1);
            assertThat(info(context)).doesNotContainKey("boilerplate");
        }
        try (ConfigurableApplicationContext context = start(true)) {
            assertThat(context.getBeansOfType(CBoilerplateInfoContributor.class)).hasSize(1);
            assertThat(context.getBean(CBoilerplateInfoContributor.class)).isSameAs(CUSTOM);
            assertThat(info(context)).containsEntry("boilerplate", Map.of("source", "custom"));
        }
    }

    private ConfigurableApplicationContext start(boolean pCustom, String... pArguments) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(CApplication.class)
                .properties("server.port=0", "server.address=127.0.0.1", "management.endpoints.web.exposure.include=info",
                        "spring.main.banner-mode=off", "logging.level.root=ERROR");
        if (pCustom) builder.sources(CCustomConfiguration.class);
        return builder.run(pArguments);
    }

    private Map<String, Object> info(ConfigurableApplicationContext pContext) throws Exception {
        int port = pContext.getEnvironment().getRequiredProperty("local.server.port", Integer.class);
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpResponse<String> response = client.send(HttpRequest.newBuilder(URI.create("http://127.0.0.1:" + port + "/actuator/info")).build(),
                    HttpResponse.BodyHandlers.ofString());
            assertThat(response.statusCode()).isEqualTo(200);
            return JsonParserFactory.getJsonParser().parseMap(response.body());
        }
    }

    @SpringBootConfiguration
    @EnableAutoConfiguration
    static class CApplication { }

    @Configuration(proxyBeanMethods = false)
    static class CCustomConfiguration {
        @Bean
        CBoilerplateInfoContributor customContributor() { return CUSTOM; }
    }
}
