package com.data.mapper.entitytodto

import com.data.dto.response.PostDto
import com.domain.entity.response.PostEntity

/**
 * Author: Ankit Singh
 * Package: com.data.mapper.entitytodto
 * Project: EITC du Assignment
 **/

fun PostEntity.toDto(): PostDto {
    return PostDto(
        body = this.body,
        id = this.id,
        title = this.title,
        userId = this.userId,
        favorite = this.favorite
    )
}