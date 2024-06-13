package com.data.mapper.dtotoentity

import com.data.dto.response.PostDto
import com.domain.entity.response.PostEntity

/**
 * Author: Ankit Singh
 * Package: com.data.mapper.dtotoentity
 * Project: EITC du Assignment
 **/

fun PostDto?.toEntity(): PostEntity {
    return PostEntity(
        body = this?.body,
        id = this?.id,
        title = this?.title,
        userId = this?.userId,
        favorite = this?.favorite ?: false
    )
}

fun List<PostDto>.map(): List<PostEntity> {
    return this.map { it.toEntity() }
}