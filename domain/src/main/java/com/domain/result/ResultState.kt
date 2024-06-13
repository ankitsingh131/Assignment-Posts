package com.domain.result

import com.domain.entity.ErrorEntity

/**
 * Author: Ankit Singh
 * Package: com.domain.result
 * Project: EITC du Assignment
 **/
sealed class ResultState<T> {

    data class Error<T>(val error: ErrorEntity) : ResultState<T>()

    data class Success<T>(val data: T) : ResultState<T>()

}