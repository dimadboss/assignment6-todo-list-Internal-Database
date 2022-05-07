package com.mmcs.todolist.presentation.main

sealed class AppScreen(val route: String) {
    object TodoListScreen : AppScreen("todo_list_screen")
}

