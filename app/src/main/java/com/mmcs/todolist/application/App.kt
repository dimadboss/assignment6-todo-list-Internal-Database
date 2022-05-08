package com.mmcs.todolist.application

import android.app.Application
import com.mmcs.todolist.data.room.TodoRepository
import com.mmcs.todolist.data.room.dao.DatabaseBuilder
import com.mmcs.todolist.data.room.dao.TodoDatabase


class App : Application() {
    companion object {
        lateinit var todoDatabase: TodoDatabase
        lateinit var todoRepository: TodoRepository
        lateinit var instance: Application
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        todoDatabase = DatabaseBuilder.build(baseContext)
        todoRepository = TodoRepository(todoDatabase.dictionaryDao())
    }

}