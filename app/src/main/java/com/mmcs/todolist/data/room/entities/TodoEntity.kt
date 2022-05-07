package com.mmcs.todolist.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = TodoEntity.TABLE_NAME)
data class TodoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val title: String,
    val completed: Boolean,
    val timestamp: Long,
) {
    companion object {
        const val TABLE_NAME = "todo"
    }
}