package ar.com.avaco.ws.rest.security.swagger;

import java.util.Arrays;
import java.util.List;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.RequestHandler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		Predicate<RequestHandler> predicate = Predicates.or(RequestHandlerSelectors.basePackage("ar.com.avaco.ws.rest"),
				RequestHandlerSelectors.basePackage("ar.com.avaco.commons.ws.controller"));

		return new Docket(DocumentationType.SWAGGER_2).select().apis(predicate).paths(PathSelectors.any()).build()
				.securitySchemes(Arrays.asList(apiKey())).securityContexts(Arrays.asList(securityContext()));
	}

	private ApiKey apiKey() {
		return new ApiKey("JWT", // nombre que aparece en Swagger
				"Authorization", // header HTTP
				"header");
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.regex("/.*")).build();
	}

	private List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");

		return Arrays.asList(new SecurityReference("JWT", new AuthorizationScope[] { authorizationScope }));
	}
}
