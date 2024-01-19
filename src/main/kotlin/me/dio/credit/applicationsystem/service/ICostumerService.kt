package me.dio.credit.applicationsystem.service

import me.dio.credit.applicationsystem.entity.Customer

interface ICostumerService {
    fun save(costumer: Customer) : Customer;
    fun findById(id : Long) : Customer;
    fun delete(id : Long) : Customer;
}