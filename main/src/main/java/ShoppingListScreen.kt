package ci.nsu.moble.main

import ShoppingViewModel
import androidx.compose.runtime.remember
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ShoppingListScreen(modifier: Modifier = Modifier)
{
    val viewModel: ShoppingViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()
    var text by remember { mutableStateOf("") }

    Column()
    {
        Row()
        {
            TextField(
                value = text,
                textStyle = TextStyle(fontSize = 25.sp),
                placeholder = { Text("Введите текст") },
                onValueChange = { newText ->
                    text = newText

                },
            )
            Button(
                onClick = {
                    viewModel.addItem()
                }, modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Добавить")
            }
        }

        Column()
        {
            uiState.items.forEach { item ->
                ShoppingItemRow(
                    item = item,
                    onCheckedChange = { viewModel.toggleItemBought(item.id) },
                    onDelete = {viewModel.deleteItem(item.id)}
                )
            }
            }
        }

    }

@Composable
fun ShoppingItemRow(
    item : ShoppingItem,
    onCheckedChange: () -> Unit,
    onDelete: () -> Unit
) {

    Row(modifier = Modifier.padding(8.dp))
    {
        Checkbox(
            checked = item.isBought,
            onCheckedChange = {onCheckedChange}
        )
        Text(text=item.name)
        Button(onClick = onDelete)
        {
            Text("Удалить")
        }
    }
}