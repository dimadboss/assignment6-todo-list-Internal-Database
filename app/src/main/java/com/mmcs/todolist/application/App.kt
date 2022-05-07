package com.mmcs.todolist.application

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.mmcs.todolist.data.room.TodoRepository
import com.mmcs.todolist.data.room.dao.DatabaseBuilder
import com.mmcs.todolist.data.room.dao.TodoDatabase


class App : Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
        lateinit var todoDatabase: TodoDatabase
        lateinit var todoRepository: TodoRepository
        lateinit var instance: Application
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
        context = baseContext
        todoDatabase = DatabaseBuilder.build(context)
        todoRepository = TodoRepository(todoDatabase.dictionaryDao())
    }

}