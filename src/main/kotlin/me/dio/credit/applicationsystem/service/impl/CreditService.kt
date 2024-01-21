package me.dio.credit.applicationsystem.service.impl

import me.dio.credit.applicationsystem.entity.Credit
import me.dio.credit.applicationsystem.exceptions.BusinessException
import me.dio.credit.applicationsystem.repository.CreditRepository
import me.dio.credit.applicationsystem.service.ICreditService
import org.springframework.stereotype.Service
import java.util.*

@Service
class CreditService (
    private val creditRepository: CreditRepository,
    private val customerService: CustomerService
): ICreditService{
    override fun save(credit: Credit): Credit {
        credit.apply {
            customer = customerService.findById(credit.customer?.id!!);
        }
        return creditRepository.save(credit)
    }

    override fun findAllByCustomer(customerId: Long): List<Credit> =
        creditRepository.findAllByCustomer(customerId)

    override fun findByCreditCode(customerId: Long, creditCode: UUID): Credit {
        val credit = creditRepository.findByCreditCode(creditCode) ?: throw BusinessException("Credit Code: $creditCode not found.");
        return if (credit.customer?.id == customerId) credit else throw IllegalArgumentException("Contact Admin");
    }
}