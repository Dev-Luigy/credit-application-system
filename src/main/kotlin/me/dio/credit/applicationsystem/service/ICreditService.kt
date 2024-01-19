package me.dio.credit.applicationsystem.service

import me.dio.credit.applicationsystem.entity.Credit
import java.util.UUID

interface ICreditService {
    fun save(credit: Credit): Credit;
    fun findAllByCustomer(customerId: Long): List<Credit>;
    fun findByCreditCode(creditCode: UUID) : Credit;
}