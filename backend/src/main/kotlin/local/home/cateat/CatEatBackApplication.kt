package local.home.cateat

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer

@SpringBootApplication
class CatEatBackApplication : SpringBootServletInitializer()

fun main(args: Array<String>) {
    runApplication<CatEatBackApplication>(*args)
}
