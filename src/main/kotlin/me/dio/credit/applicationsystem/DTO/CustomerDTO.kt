package me.dio.credit.applicationsystem.DTO

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.PositiveOrZero
import jakarta.validation.constraints.Size
import me.dio.credit.applicationsystem.entity.Address
import me.dio.credit.applicationsystem.entity.Customer
import org.hibernate.validator.constraints.br.CPF
import org.jetbrains.annotations.NotNull
import java.math.BigDecimal

data class CustomerDTO (
    @field:NotNull(value = "First name must be not empty")    val firstName : String,
    @field:NotNull(value = "Last name must be not empty")     val lastName : String,
    @field:CPF(message = "CPF must be not empty")             val cpf : String,
    @field:PositiveOrZero(message="Income must be >= 0")      val income : BigDecimal,
    @field:Email(message = "Email must be valid")             val email : String,
    @field:NotNull(value = "password must be not empty")      val password : String,
    @field:NotNull(value = "ZipCode must be not empty")
    @field:Size(min = 8, max = 8)                             val zipCode : String,
    @field:NotNull(value = "Street must be not empty")        val street : String
) {
    fun toEntity() : Customer = Customer(
        firstName = this.firstName,
        lastName = this.lastName,
        cpf = this.cpf,
        income = this.income,
        email = this.email,
        password = this.password,
        address = Address(
            zipCode = this.zipCode,
            street = this.street
        )


    )
}
