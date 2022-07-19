package kot.microservices.reactive.functional

import kot.microservices.reactive.Customer
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.router
import reactor.kotlin.core.publisher.toMono

@Component
class CustomerRouter(private val customerHandler: CustomerHandler) { // @Autowired 대신 생성자 주입하는 방식
//    @Autowired
//    lateinit var customerHandler: CustomerHandler

    @Bean
    fun customerRoutes(): RouterFunction<*> = router {
        "/functional".nest {
            "/customer".nest {
//                GET("/") {
//                     it:ServerRequest -> customerHandler.get(it)
//                }

                GET("/{id}", customerHandler::get)

                POST("/", customerHandler::create)
            }

            "/customer2".nest {
                GET("/") { it: ServerRequest ->
                    println("uri = ${it.uri()} headers = ${it.headers()} cookie = ${it.cookies()} session = ${it.session()}")
                    ok().body(Customer(2, "functional web2").toMono(), Customer::class.java)

                }
            }

            "/customers".nest {
                GET("/", customerHandler::search)
            }
        }
    }


}