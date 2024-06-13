package com.data.dto.response

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Author: Ankit Singh
 * Package: com.data.dto.response
 * Project: EITC du Assignment
 **/

@Entity(tableName = "posts")
data class PostDto(
    @ColumnInfo(name = "body") @SerializedName("body") val body: String?,
    @PrimaryKey @ColumnInfo(name = "id") @SerializedName("id") val id: Int?,
    @ColumnInfo(name = "title") @SerializedName("title") val title: String?,
    @ColumnInfo(name = "userId") @SerializedName("userId") val userId: Int?,
    @ColumnInfo(name = "favorite") var favorite: Boolean = false
)