package com.mmcs.todolist.data.room

import com.mmcs.todolist.data.room.dao.TodoDao
import com.mmcs.todolist.data.room.entities.TodoEntity

class TodoRepository(private val dictionaryDao: TodoDao) {

    suspend fun selectAllTodos(): List<TodoEntity> = dictionaryDao.selectAllItems()

    suspend fun insertTodo(todo: TodoEntity) = dictionaryDao.insertItem(todo)

    suspend fun deleteTodo(todo: TodoEntity) = dictionaryDao.deleteItem(todo)

    suspend fun updateTodo(todo: TodoEntity) = dictionaryDao.updateItem(todo)

    suspend fun getItemByTimestamp(timestamp: Long) : TodoEntity = dictionaryDao.getItemByTimestamp(timestamp)
}