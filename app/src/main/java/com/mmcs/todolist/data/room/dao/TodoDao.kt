package com.mmcs.todolist.data.room.dao

import androidx.room.*
import com.mmcs.todolist.data.room.entities.TodoEntity

@Dao
interface TodoDao {

    @Query("SELECT * FROM ${TodoEntity.TABLE_NAME} ORDER BY timestamp DESC")
    suspend fun selectAllItems(): List<TodoEntity>

    @Insert
    suspend fun insertItem(todo: TodoEntity)

    @Delete
    suspend fun deleteItem(todo: TodoEntity)

    @Update
    suspend fun updateItem(todo: TodoEntity)

    @Query("SELECT * FROM ${TodoEntity.TABLE_NAME} WHERE timestamp=:timestamp ORDER BY timestamp DESC LIMIT 1")
    suspend fun getItemByTimestamp(timestamp: Long): TodoEntity
}