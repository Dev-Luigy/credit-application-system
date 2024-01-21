package me.dio.credit.applicationsystem.controller

import jakarta.validation.Valid
import me.dio.credit.applicationsystem.DTO.CreditDTO
import me.dio.credit.applicationsystem.DTO.CreditView
import me.dio.credit.applicationsystem.DTO.CreditViewList
import me.dio.credit.applicationsystem.entity.Credit
import me.dio.credit.applicationsystem.service.impl.CreditService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
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
    fun saveCredit(@RequestBody @Valid creditDto : CreditDTO) : ResponseEntity<String>{
        val credit : Credit = creditService.save(creditDto.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED).body("Credit ${credit.creditCode} - Customer ${credit.customer?.firstName} saved!")
    }

    @GetMapping
    fun findAllByCustomerId(@RequestParam(value = "customerId") customerId : Long) : ResponseEntity<List<CreditViewList>> {
        return ResponseEntity.status(HttpStatus.OK).body(
            creditService.findAllByCustomer(customerId)
            .stream()
            .map { credit: Credit -> CreditViewList(credit) }
            .collect(Collectors.toList()))
    }

    @GetMapping("/{creditCode}")
    fun findByCreditCode(@RequestParam(value = "customerId") customerId : Long,
                         @PathVariable creditCode : UUID) :ResponseEntity<CreditView>{
        val credit = creditService.findByCreditCode(customerId, creditCode)
        return ResponseEntity.status(HttpStatus.OK).body(CreditView(credit))
    }
}