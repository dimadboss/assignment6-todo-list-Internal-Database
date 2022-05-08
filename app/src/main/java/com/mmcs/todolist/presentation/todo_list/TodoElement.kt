package com.mmcs.todolist.presentation.todo_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import com.mmcs.todolist.data.room.entities.TodoEntity

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