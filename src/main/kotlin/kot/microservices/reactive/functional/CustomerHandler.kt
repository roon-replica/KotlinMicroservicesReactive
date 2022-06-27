package kot.microservices.reactive.functional

import kot.microservices.reactive.Customer
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

@Component
class CustomerHandler {
    fun get(serverRequest: ServerRequest): Mono<ServerResponse> {
        return ok().body(Customer(1, "functional web").toMono(), Customer::class.java)
    }

    // 타입 추론을 이용해서 statement 대신 expression으로
    // fun get(serverRequest: ServerRequest) = ok().body(Customer(1,"functional web").toMono(),Customer::class.java)
}