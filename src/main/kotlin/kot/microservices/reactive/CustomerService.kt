package kot.microservices.reactive

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface CustomerService {
    fun getCustomer(id: Int): Mono<Customer>
    fun searchCustomers(nameFilter: String): Flux<Customer>  // 리스트가 아니라 그냥 타입을 써줘야하네
    fun createCustomer(customerMono: Mono<Customer>): Mono<*> // TODO: generic 안에 별표는 뭐더라..
}