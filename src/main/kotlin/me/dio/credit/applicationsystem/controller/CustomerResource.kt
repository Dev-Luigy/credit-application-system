package me.dio.credit.applicationsystem.controller

import jakarta.validation.Valid
import me.dio.credit.applicationsystem.DTO.CustomerView
import me.dio.credit.applicationsystem.DTO.CustomerDTO
import me.dio.credit.applicationsystem.DTO.CustomerUpdateDTO
import me.dio.credit.applicationsystem.entity.Customer
import me.dio.credit.applicationsystem.service.impl.CostumerService
import org.apache.coyote.Response
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/customers")
class CustomerResource (
    private val customerService: CostumerService
){
    @PostMapping
    fun saveCustomer(@RequestBody @Valid customerdto : CustomerDTO) : ResponseEntity<String>{
        val savedCustomer = customerService.save(customerdto.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED).body("Customer ${savedCustomer.email} saved!")
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id : Long) : ResponseEntity<CustomerView> {
        val customer : Customer = customerService.findById(id)
        return ResponseEntity.status(HttpStatus.OK).body(CustomerView(customer));
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id : Long) : ResponseEntity<String>{
        val customer = customerService.findById(id);
        customerService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Custumer: ${customer.email} deleted!")
    }

    @PatchMapping
    fun updateCustomer(@RequestParam(value = "customerId") id : Long, @RequestBody @Valid customerUpdateDto : CustomerUpdateDTO) : ResponseEntity<CustomerView>{
        val customer = customerService.findById(id);
        val customerToUpdate = customerUpdateDto.toEntity(customer);
        val customerUpdated = customerService.save(customerToUpdate)
        return ResponseEntity.status(HttpStatus.OK).body(CustomerView(customerUpdated))
    }
}