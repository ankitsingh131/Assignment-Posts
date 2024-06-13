package com.data.repository

import com.data.constant.NetworkConstants
import com.domain.entity.ErrorEntity
import com.domain.repository.BaseRepository
import com.domain.result.ResultState
import java.io.IOException

/**
 * Author: Ankit Singh
 * Package: com.data.repository
 * Project: EITC du Assignment
 **/
open class BaseRepositoryImpl : BaseRepository {

    internal fun handleError(throwable: Throwable): ErrorEntity {
        return when (throwable) {
            is IOException -> {
                ErrorEntity(
                    NetworkConstants.NO_INTERNET_ERROR_CODE,
                    NetworkConstants.NO_INTERNET_ERROR_MESSAGE
                )
            }

            else -> {
                ErrorEntity(
                    NetworkConstants.UNEXPECTED_ERROR_CODE,
                    NetworkConstants.UNEXPECTED_ERROR_MESSAGE
                )
            }
        }
    }
}