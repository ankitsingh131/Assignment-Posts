package com.domain.entity.response

data class PostEntity(
    val body: String?,
    val id: Int?,
    val title: String?,
    val userId: Int?,
    var favorite: Boolean = false
)