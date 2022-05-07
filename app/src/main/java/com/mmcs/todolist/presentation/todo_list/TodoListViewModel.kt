package com.mmcs.todolist.presentation.todo_list

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mmcs.todolist.application.App
import com.mmcs.todolist.data.room.entities.TodoEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import kotlin.random.Random

class TodoListViewModel : AndroidViewModel(App.instance) {
    fun onDelete(todo: TodoEntity) {
        viewModelScope.launch {
            App.todoRepository.deleteTodo(todo)
            val newList = _todoList.value?.filterNot { it.id == todo.id }
            _todoList.postValue(newList)
        }
    }

    fun onCheckClicked(todo: TodoEntity) {
        viewModelScope.launch {
            val newValue = todo.copy(completed = !todo.completed)
            App.todoRepository.updateTodo(newValue)
            val newList = _todoList.value?.map {
                if (it.id == todo.id) newValue else it
            }
            _todoList.postValue(newList)
        }
    }

    fun addTodo(item: TodoEntity) {
        viewModelScope.launch {
            App.todoRepository.insertTodo(item)
            _todoList.postValue(App.todoRepository.selectAllTodos())
        }
    }

    private val todoListFlow = flow {
        while (_todoList.value.isNullOrEmpty()) {
            val todos = App.todoRepository.selectAllTodos()
            emit(todos)
            kotlinx.coroutines.delay(1000)
        }
    }
    private val _todoList = MutableLiveData<List<TodoEntity>>(listOf())
    val todoList: LiveData<List<TodoEntity>>
        get() = _todoList

    init {
        viewModelScope.launch(Dispatchers.IO) {
            todoListFlow.collectLatest {
                if (it != todoList.value)
                    _todoList.postValue(it)
            }
        }
    }
}