package me.dio.credit.applicationsystem.exceptions

data class BusinessException (
    override val message: String?
) : RuntimeException(message){

}