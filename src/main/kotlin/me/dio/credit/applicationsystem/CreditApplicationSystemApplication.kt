package me.dio.credit.applicationsystem

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["boot.registration"])
@Suppress("SpringBootConfiguration")
class CreditApplicationSystemApplication

fun main(args: Array<String>) {
	runApplication<CreditApplicationSystemApplication>(*args)
}
