package api.service.transfer.configuration;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi apiTransfers() {
        return GroupedOpenApi.builder()
                .group("api-transfer")
                .pathsToMatch("/api/transfers/**")
                .build();
    }
}
