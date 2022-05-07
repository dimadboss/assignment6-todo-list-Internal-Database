package com.mmcs.todolist.data.room.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mmcs.todolist.data.room.entities.TodoEntity

@Database(
    entities = [TodoEntity::class],
    version = 1,
    exportSchema = false
)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun dictionaryDao(): TodoDao

    companion object {
        const val DATABASE_NAME = "todo_room_database"
    }
}

