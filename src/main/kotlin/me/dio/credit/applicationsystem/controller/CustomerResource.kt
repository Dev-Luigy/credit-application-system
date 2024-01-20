package me.dio.credit.applicationsystem.controller

import me.dio.credit.applicationsystem.DTO.CustomerView
import me.dio.credit.applicationsystem.DTO.CustomerDTO
import me.dio.credit.applicationsystem.DTO.CustomerUpdateDTO
import me.dio.credit.applicationsystem.entity.Customer
import me.dio.credit.applicationsystem.service.impl.CostumerService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/customer")
class CustomerResource (
    private val customerService: CostumerService
){
    @PostMapping
    fun saveCustomer(@RequestBody customerdto : CustomerDTO) : String{
        val savedCustomer = customerService.save(customerdto.toEntity())
        return "Customer ${savedCustomer.email} saved!"
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id : Long) : CustomerView {
        val customer : Customer = customerService.findById(id)
        return CustomerView(customer);
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id : Long){
        customerService.delete(id);
    }

    @PatchMapping
    fun updateCustomer(@RequestParam(value = "customerId") id : Long, @RequestBody customerUpdateDto : CustomerUpdateDTO) : CustomerView {
        val customer = customerService.findById(id);
        val customerToUpdate = customerUpdateDto.toEntity(customer);
        val customerUpdated = customerService.save(customerToUpdate)
        return CustomerView(customerUpdated)
    }
}