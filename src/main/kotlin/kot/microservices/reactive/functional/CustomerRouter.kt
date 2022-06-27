package kot.microservices.reactive.functional

import kot.microservices.reactive.Customer
import kot.microservices.reactive.CustomerHandler
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.router
import reactor.kotlin.core.publisher.toMono

@Component
class CustomerRouter(private val customerHandler: CustomerHandler) {
//    @Autowired
//    lateinit var customerHandler: CustomerHandler

    @Bean
    fun customerRoutes(): RouterFunction<*> = router {
        "/functional".nest {
            "/customer".nest {
//                GET("/") {
//                     it:ServerRequest -> customerHandler.get(it)
//                }

                GET("/", customerHandler::get)
            }

            "/customer2".nest {
                GET("/") { it: ServerRequest ->
                    println(it)
                    println(it.attributes())
                    println(it.cookies())
                    println(it.session())
                    ok().body(Customer(2, "functional web2").toMono(), Customer::class.java)

                }
            }
        }
    }


}