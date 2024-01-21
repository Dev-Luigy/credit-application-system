package me.dio.credit.applicationsystem.DTO

import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.PositiveOrZero
import me.dio.credit.applicationsystem.entity.Credit
import me.dio.credit.applicationsystem.entity.Customer
import org.springframework.format.annotation.DateTimeFormat
import java.math.BigDecimal
import java.time.LocalDate


data class CreditDTO(
    @field:Positive(message = "creditValue must be > 0")            val creditValue : BigDecimal,
    @field:DateTimeFormat                                           val dayFirstOfInstallment : LocalDate,
    @field:Positive(message = "Number of Installments must be > 0") val numberOfInstallments : Int,
    @field:Positive(message = "Customer ID must be positive")       val customerId : Long
) {
    fun toEntity() : Credit = Credit(
        creditValue = this.creditValue,
        dayFirstInstallment = this.dayFirstOfInstallment,
        numberOfInstallments = this.numberOfInstallments,
        customer = Customer(id = this.customerId)
    )
}
