package local.home.cateat.system

import io.swagger.models.auth.In.HEADER
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders.AUTHORIZATION
import org.springframework.web.bind.annotation.RestController
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.ApiKey
import springfox.documentation.service.AuthorizationScope
import springfox.documentation.service.SecurityReference
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spi.service.contexts.SecurityContext
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SpringFoxConfig {

    @Bean
    fun customImplementation(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo)
            .securitySchemes(listOf(apiKey))
            .securityContexts(listOf(securityContext))
            .select()
            .apis(RequestHandlerSelectors.withClassAnnotation(RestController::class.java))
            .paths(PathSelectors.any())
            .build()
            .enable(true)
    }

    private val apiInfo: ApiInfo
        get() {
            val title = APP_NAME
            return ApiInfoBuilder()
                .title(title)
                .description(title)
                .build()
        }

    private val apiKey: ApiKey
        get() = ApiKey(API_KEY, AUTHORIZATION, HEADER.name)

    private val securityContext: SecurityContext
        get() = SecurityContext.builder()
            .securityReferences(defaultAuth)
            .build()

    private val defaultAuth: List<SecurityReference>
        get() {
            val authorizationScope = AuthorizationScope("global", "accessEverything")
            val authorizationScopes = arrayOf(authorizationScope)
            return listOf(SecurityReference(API_KEY, authorizationScopes))
        }

    companion object {
        private const val APP_NAME = "Swagger (Cat Eat)"
        private const val API_KEY = "JWT"

        val SWAGGER_ENDPOINTS = arrayOf(
            "/v2/api-docs",
            "/swagger.json",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/v3/api-docs/**",
            "/swagger-ui/**" // other public endpoints of your API may be appended to this array
        )
    }
}