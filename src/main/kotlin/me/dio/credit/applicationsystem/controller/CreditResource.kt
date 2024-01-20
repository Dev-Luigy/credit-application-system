package me.dio.credit.applicationsystem.controller

import me.dio.credit.applicationsystem.DTO.CreditDTO
import me.dio.credit.applicationsystem.DTO.CreditView
import me.dio.credit.applicationsystem.DTO.CreditViewList
import me.dio.credit.applicationsystem.entity.Credit
import me.dio.credit.applicationsystem.service.impl.CreditService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID
import java.util.stream.Collectors


@RestController
@RequestMapping("/api/credits")
class CreditResource(
    private val creditService: CreditService
){
    @PostMapping
    fun saveCredit(@RequestBody creditDto : CreditDTO) : String{
        val credit : Credit = creditService.save(creditDto.toEntity())
        return "Credit ${credit.creditCode} - Customer ${credit.customer?.firstName} saved!"
    }

    @GetMapping
    fun findAllByCustomerId(@RequestParam(value = "customerId") customerId : Long) : List<CreditViewList> {
        return creditService.findAllByCustomer(customerId)
            .stream()
            .map { credit: Credit -> CreditViewList(credit) }
            .collect(Collectors.toList())
    }

    @GetMapping
    fun findByCreditCode(@RequestParam(value = "customerID") customerId : Long,
                         @PathVariable creditCode : UUID) : CreditView{
        val credit = creditService.findByCreditCode(customerId, creditCode)
        return CreditView(credit)
    }
}