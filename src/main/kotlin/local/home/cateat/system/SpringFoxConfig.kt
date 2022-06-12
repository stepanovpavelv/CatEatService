package local.home.cateat.system

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.RestController
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SpringFoxConfig {

    @Bean
    fun customImplementation(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo)
            .select()
            .apis(RequestHandlerSelectors.withClassAnnotation(RestController::class.java))
            .build()
            .enable(true)
    }

    private val apiInfo: ApiInfo
        get() {
            val title = "Swagger (Cat Eat)"
            return ApiInfoBuilder()
                .title(title)
                .description(title)
                .build()
        }
}