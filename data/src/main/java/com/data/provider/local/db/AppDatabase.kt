package com.data.provider.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.data.dto.response.PostDto
import com.data.provider.local.dao.PostDao

/**
 * Author: Ankit Singh
 * Package: com.data.provider.local.db
 * Project: EITC du Assignment
 **/

@Database(entities = [PostDto::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun postDao(): PostDao
}