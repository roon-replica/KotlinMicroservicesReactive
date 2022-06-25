package kot.microservices.reactive

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface CustomerService {
    fun getCustomer(id: Int): Mono<Customer>?
    fun searchCustomers(nameFilter: String): Flux<Customer>  // 리스트가 아니라 그냥 타입을 써줘야하네
}