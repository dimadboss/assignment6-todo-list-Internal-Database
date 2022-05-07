package com.mmcs.todolist.data.room.dao

import android.content.Context
import androidx.room.Room

object DatabaseBuilder {
    fun build(context: Context) = Room.databaseBuilder(
        context,
        TodoDatabase::class.java,
        TodoDatabase.DATABASE_NAME,
    ).fallbackToDestructiveMigration().build()
}