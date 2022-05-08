package com.mmcs.todolist.presentation.todo_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.ExperimentalUnitApi
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
                    timestamp = System.currentTimeMillis()
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