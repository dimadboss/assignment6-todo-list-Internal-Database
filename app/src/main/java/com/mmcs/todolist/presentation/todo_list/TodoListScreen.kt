package com.mmcs.todolist.presentation.todo_list

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.mmcs.todolist.data.room.entities.TodoEntity

@ExperimentalUnitApi
@Composable
fun AddNewItemRow(
    viewModel: TodoListViewModel
) {
    var text by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp, 5.dp, 0.dp, 5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            textStyle = normalStyle,
            label = { Text("New to-do element") },
            maxLines = 2,
        )
        IconButton(onClick = {
            if (text.isEmpty()) {
                return@IconButton
            }
            viewModel.addTodo(
                TodoEntity(
                    title = text,
                    completed = false,
                )
            )
            text = ""
        }) {
            Icon(
                Icons.Default.Add,
                null,
            )
        }
    }
}

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

@ExperimentalUnitApi
val completedStyle = TextStyle(
    textDecoration = TextDecoration.LineThrough,
    fontSize = TextUnit(16F, TextUnitType.Sp)
)

@ExperimentalUnitApi
val normalStyle = TextStyle(
    fontSize = TextUnit(16F, TextUnitType.Sp),
    fontWeight = FontWeight.W700,
)

@ExperimentalUnitApi
fun buildTextStyle(elem: TodoEntity): TextStyle {
    return if (elem.completed) completedStyle else normalStyle
}

@ExperimentalUnitApi
@Composable
fun TodoElement(
    todo: TodoEntity,
    onCheckClicked: (Boolean) -> Unit,
    onDelete: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = todo.completed,
                onCheckedChange = onCheckClicked,
            )
            Text(
                text = todo.title,
                style = buildTextStyle(todo)
            )
        }
        IconButton(onClick = onDelete) {
            Icon(
                Icons.Default.DeleteForever,
                null,
            )
        }
    }
}