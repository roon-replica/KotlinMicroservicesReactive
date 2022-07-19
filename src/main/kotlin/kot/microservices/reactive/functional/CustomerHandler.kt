package kot.microservices.reactive.functional

import kot.microservices.reactive.Customer
import kot.microservices.reactive.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters.fromObject
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.*
import org.springframework.web.reactive.function.server.body
import org.springframework.web.reactive.function.server.bodyToMono
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

//@Component
//class CustomerHandler {
//    fun get(serverRequest: ServerRequest): Mono<ServerResponse> {
//        return ok().body(Customer(1, "functional web").toMono(), Customer::class.java)
//    }
//
//    // 타입 추론을 이용해서 statement 대신 expression으로
//    // fun get(serverRequest: ServerRequest) = ok().body(Customer(1,"functional web").toMono(),Customer::class.java)
//}

@Component
class CustomerHandler(val customerService: CustomerService) {
//    fun get(serverRequest: ServerRequest) =
//        ok().body(customerService.getCustomer(serverRequest.pathVariable("id").toInt()), Customer::class.java)

    fun get(serverRequest: ServerRequest) =
        customerService.getCustomer(serverRequest.pathVariable("id").toInt())
            .flatMap { ok().body(fromObject(it)) }        // fromObject메서드 deprecated 됐다고 뜸
//            .switchIfEmpty(notFound().build())
            .switchIfEmpty(status(HttpStatus.NOT_FOUND).build())

    fun search(serverRequest: ServerRequest) =
//        ok().body(customerService.searchCustomers(""),Customer::class.java)
        ok().body(
            customerService.searchCustomers(serverRequest.queryParam("nameFilter").orElse("")),
            Customer::class.java
        )

    fun create(serverRequest: ServerRequest) =
        ok().body(
            customerService.createCustomer(serverRequest.bodyToMono()).flatMap {
                status(HttpStatus.CREATED).body(fromObject(it))
            }
        )

}