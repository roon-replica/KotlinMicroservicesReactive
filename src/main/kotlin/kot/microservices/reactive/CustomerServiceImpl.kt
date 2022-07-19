package kot.microservices.reactive

import kotlinx.coroutines.reactor.awaitSingle
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

    override fun getCustomer(id: Int): Mono<Customer> = customers[id]?.toMono() ?: Mono.empty()

    override fun searchCustomers(nameFilter: String): Flux<Customer> =  // 리턴타입 생략해도 되지
        customers.filter { it.value.name.contains(nameFilter, true) }
            .map { e -> e.value }
            .toList()
            .toFlux()

    override fun createCustomer(customerMono: Mono<Customer>): Mono<*> {
        // TODO: "Calling 'subscribe' in non-blocking context is not recommended " <- 이런 경고 뜸
        // TODO: 그리고 이렇게 하면 customers에 추가가 안 됨. 비동기적으로 타이밍이 안맞나 봄.. 아직 원인은 모르겠음

        // TODO: 정상 동작함...
        return customerMono.subscribe {
            customers[it.id] = it
        }.toMono()


//        return customerMono.map {
//            customers[it.id] = it
//            it
//        }
    }
}