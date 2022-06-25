package kot.microservices.reactive

import reactor.core.publisher.Mono

interface CustomerService {
    fun getCustomer(id: Int): Mono<Customer>?
    fun searchCustomers(nameFilter: String): List<Customer>
}