package kot.microservices.reactive

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.Locale.IsoCountryCode

data class Customer(var id: Int = 0, val name: String = "", val telephone: Telephone? = null)
data class Telephone(var countryCode: String = "", var telephoneNumber: String = "")

@RestController
class CustomerController {
    @Autowired
    lateinit var customerService: CustomerService

    @GetMapping("/customer/{id}")
    fun getCustomer(@PathVariable id: Int): ResponseEntity<Customer> {
        return ResponseEntity.ok(customerService.getCustomer(id))
    }

    // 이렇게 expression으로 쓰면 리턴타입 생략 가능
    @GetMapping("/customers")
    fun getCustomersByNameFilter(@RequestParam(required = false, defaultValue = "") nameFilter: String) =
        ResponseEntity.ok(customerService.searchCustomers(nameFilter))

}