package me.dio.credit.applicationsystem.DTO

import jakarta.validation.constraints.PositiveOrZero
import jakarta.validation.constraints.Size
import me.dio.credit.applicationsystem.entity.Customer
import org.jetbrains.annotations.NotNull
import java.math.BigDecimal

data class CustomerUpdateDTO (
    @field:NotNull(value = "First name must be not empty")    val firstName : String,
    @field:NotNull(value = "Last name must be not empty")     val lastName : String,
    @PositiveOrZero(message = "Income must be >= 0")          val income : BigDecimal,
    @field:NotNull(value = "ZipCode must be not empty")
    @field:Size(min = 8, max = 8)                             val zipCode : String,
    @field:NotNull(value = "Street must be not empty")        val street : String
) {
    fun toEntity(customer : Customer) : Customer{
        customer.firstName = firstName
        customer.lastName = lastName
        customer.income = income
        customer.address.zipCode = zipCode
        customer.address.street = street
        return customer
    }
}
