package com.data.mapper.dtotoentity

import com.data.dto.ErrorDto
import com.domain.entity.ErrorEntity

/**
 * Author: Ankit Singh
 * Package: com.data.mapper.dtotoentity
 * Project: EITC du Assignment
 **/

fun ErrorDto?.toEntity(): ErrorEntity? {
    return this?.let { ErrorEntity(errorCode = this.errorCode, errorMessage = this.errorMessage) }
}