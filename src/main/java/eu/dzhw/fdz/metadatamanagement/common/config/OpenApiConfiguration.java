package eu.dzhw.fdz.metadatamanagement.common.config;

import org.apache.commons.lang3.StringUtils;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

/**
 * Enable swagger for all API endpoints.
 */
@Configuration
public class OpenApiConfiguration {

  /**
   * Generate OpenAPI documentation.
   */
  @Bean
  public OpenAPI openApi() {
    return new OpenAPI().components(new Components())
        .info(new Info().title("Metadatamanagement API")
            .description("This is the UI of the OpenAPI 3 documentation of the MDM."));
  }

  /**
   * Hide undocumented API operations from documentation for dev, test and prod.
   * @return A modified OpenAPI
   */
  @Bean
  @Profile("!" + Constants.SPRING_PROFILE_LOCAL)
  public OpenApiCustomiser hideUndocumentedApiOperations() {
    return openApi -> {
      openApi.getPaths().values().stream()
          .forEach(pathItem -> pathItem.readOperationsMap().forEach((method, operation) -> {
            if (StringUtils.isEmpty(operation.getSummary())) {
              switch (method) {
                case DELETE:
                  pathItem.setDelete(null);
                  break;
                case GET:
                  pathItem.setGet(null);
                  break;
                case HEAD:
                  pathItem.setHead(null);
                  break;
                case OPTIONS:
                  pathItem.setOptions(null);
                  break;
                case PATCH:
                  pathItem.setPatch(null);
                  break;
                case POST:
                  pathItem.setPost(null);
                  break;
                case PUT:
                  pathItem.setPut(null);
                  break;
                case TRACE:
                  pathItem.setTrace(null);
                  break;
                default:
                  break;
              }
            }
          }));
    };
  }

}
