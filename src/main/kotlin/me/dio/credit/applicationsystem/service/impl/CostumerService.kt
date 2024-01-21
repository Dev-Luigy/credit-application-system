package me.dio.credit.applicationsystem.service.impl

import me.dio.credit.applicationsystem.entity.Customer
import me.dio.credit.applicationsystem.repository.CustomerRepository
import me.dio.credit.applicationsystem.service.ICostumerService
import org.springframework.stereotype.Service

@Service
class CostumerService (
    private val customerRepository: CustomerRepository
): ICostumerService {
    override fun save(customer: Customer): Customer =
        customerRepository.save(customer);
    override fun findById(id: Long): Customer =
        customerRepository.findById(id).orElseThrow{
            throw RuntimeException("Id $id not found");
        }

    override fun delete(id: Long) =
        customerRepository.deleteById(id);

}