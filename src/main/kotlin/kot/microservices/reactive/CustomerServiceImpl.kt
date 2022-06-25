package kot.microservices.reactive

import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toFlux
import reactor.kotlin.core.publisher.toMono
import java.util.concurrent.ConcurrentHashMap

@Service
class CustomerServiceImpl : CustomerService {
    companion object {
        val sampleCustomers = arrayOf(
            Customer(1, "zett"),
            Customer(2, "henry"),
            Customer(3, "jackson")
        )
    }

    val customers = ConcurrentHashMap<Int, Customer>(sampleCustomers.associateBy(Customer::id))

    override fun getCustomer(id: Int): Mono<Customer>? = customers[id]?.toMono()

    override fun searchCustomers(nameFilter: String): Flux<Customer> =  // 리턴타입 생략해도 되지
        customers.filter { it.value.name.contains(nameFilter, true) }
            .map { e -> e.value }
            .toList()
            .toFlux()
}