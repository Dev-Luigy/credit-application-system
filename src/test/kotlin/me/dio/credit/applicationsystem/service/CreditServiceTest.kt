package me.dio.credit.applicationsystem.service

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.unmockkAll
import io.mockk.verify
import me.dio.credit.applicationsystem.entity.Credit
import me.dio.credit.applicationsystem.entity.Customer
import me.dio.credit.applicationsystem.exceptions.BusinessException
import me.dio.credit.applicationsystem.repository.CreditRepository
import me.dio.credit.applicationsystem.repository.CustomerRepository
import me.dio.credit.applicationsystem.service.impl.CreditService
import me.dio.credit.applicationsystem.service.impl.CustomerService
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.ActiveProfiles
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*
import kotlin.random.Random

@ExtendWith(MockKExtension::class)
class CreditServiceTest {
    @MockK
    lateinit var creditRepository: CreditRepository

    @MockK
    lateinit var customerService: CustomerService

    @InjectMockKs
    lateinit var creditService: CreditService

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
        //creditService = CreditService(creditRepository, customerService)
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }
    @Test
    fun `should save credit`() {
        // given
        val customerId : Long = 1L;
        val fakeCredit : Credit = buildCredit()

        every { customerService.findById(customerId) } returns fakeCredit.customer!!
        every { creditRepository.save(fakeCredit) } returns fakeCredit

        // when
        val savedCredit: Credit = creditService.save(fakeCredit)

        // then
        Assertions.assertThat(savedCredit).isNotNull
        Assertions.assertThat(savedCredit).isSameAs(fakeCredit)
        verify(exactly = 1) { customerService.findById(customerId) }
        verify(exactly = 1) { creditRepository.save(fakeCredit) }

    }
    @Test
    fun `should not create credit when invalid day first installment`() {
        //given
        val fakeDate : LocalDate = LocalDate.now().plusMonths(-3)
        val fakeCredit : Credit = buildCredit(dayFirstInstallment = fakeDate)
        every { creditRepository.save(fakeCredit)} answers { fakeCredit }
        //when
        Assertions.assertThatThrownBy { creditService.save(fakeCredit) }
            .isInstanceOf(BusinessException::class.java)
            .hasMessage("Invalid Date")
        //then


    }

    companion object {
        private fun buildCredit(
            creditValue: BigDecimal = BigDecimal.valueOf(100.0),
            dayFirstInstallment: LocalDate = LocalDate.now().plusMonths(2L),
            numberOfInstallments: Int = 15,
            customer: Customer = CustomerServiceTest.buildCustomer()
        ): Credit = Credit(
            creditValue = creditValue,
            dayFirstInstallment = dayFirstInstallment,
            numberOfInstallments = numberOfInstallments,
            customer = customer
        )
    }
}