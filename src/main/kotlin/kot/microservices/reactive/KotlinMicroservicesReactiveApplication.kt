package kot.microservices.reactive

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinMicroservicesReactiveApplication

fun main(args: Array<String>) {
    runApplication<KotlinMicroservicesReactiveApplication>(*args)
}
