package me.dio.credit.applicationsystem.DTO

import me.dio.credit.applicationsystem.entity.Customer
import java.math.BigDecimal

data class CustomerUpdateDTO (
    val firstName: String,
    val lastName : String,
    val income : BigDecimal,
    val zipCode : String,
    val street : String
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
