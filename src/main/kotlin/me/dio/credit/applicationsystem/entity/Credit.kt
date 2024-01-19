package me.dio.credit.applicationsystem.entity

import me.dio.credit.applicationsystem.enummeration.Status
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

data class Credit (
    val creditCode: UUID = UUID.randomUUID(),
    val creditValue: BigDecimal = BigDecimal.ZERO,
    val dayFirstInstallment: LocalDate,
    val numberOfInstallments: Int = 0,
    val status: Status,
    var customer: Customer? = null,
    val id: Long? = null
)
