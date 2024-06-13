package com.domain.usecase

/**
 * Author: Ankit Singh
 * Package: com.domain.usecase
 * Project: EITC du Assignment
 **/
interface BaseUseCase<P, T> {

    fun execute(param: P): T
}