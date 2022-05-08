package com.mmcs.todolist.presentation.todo_list

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.unit.ExperimentalUnitApi


@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalUnitApi
@Composable
fun TodoListScreen(
    viewModel: TodoListViewModel = TodoListViewModel()
) {
    val todos by viewModel.todoList.observeAsState(initial = emptyList())
    Scaffold {
        it
        Column {
            AddNewItemRow(viewModel)
            LazyColumn {
                items(todos) { todo ->
                    TodoElement(todo = todo, onCheckClicked = {
                        viewModel.onCheckClicked(todo)
                    }) {
                        viewModel.onDelete(todo)
                    }
                }
            }
        }

    }
}

