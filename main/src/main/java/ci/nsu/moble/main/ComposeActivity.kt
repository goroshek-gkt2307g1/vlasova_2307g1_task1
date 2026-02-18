package ci.nsu.moble.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ci.nsu.moble.main.ui.theme.PracticeTheme
import androidx.compose.ui.graphics.Color
import android.util.Log
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Magenta

class ComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApp()
        }
    }
}

private val colorsMap = mapOf(
    "Red" to Red,
    "Orange" to Color(0xFFFFA500), //оранжевый
    "Yellow" to Yellow,
    "Green" to Green,
    "Blue" to Blue,
    "Indigo" to Color(0xFF4B0082), //индиго
    "Violet" to Color(0xFFEE82EE), //фиолетовый
    "Magenta" to Magenta,
    "Black" to Black
)

@Composable
fun MyApp() {
    Column(
        modifier = Modifier
            .padding(60.dp)
            .size(800.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val message = remember { mutableStateOf("") }
        val buttonColor = remember { mutableStateOf<Color?>(null) }
        val errorMessage = remember { mutableStateOf("") }

        Column {
            TextField(
                value = message.value,
                textStyle = TextStyle(fontSize = 25.sp),
                placeholder = { Text("Введите цвет") },
                onValueChange = { newText ->
                    message.value = newText
                    errorMessage.value = ""
                }
            )
        }

        Button(
            onClick = {
                val foundColor = colorsMap[message.value]
                if (foundColor != null) {
                    buttonColor.value = foundColor
                    Log.d("ColorSearch", "Цвет '${message.value}' найден и применен")
                } else {
                    buttonColor.value = null
                    Log.d("ColorSearch", "Пользовательский цвет '${message.value}' не найден")
                    errorMessage.value = "Цвет «${message.value}» не найден"
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = buttonColor.value ?: Color.Gray
            ),
            modifier = Modifier
                .padding(0.dp, 5.dp, 0.dp, 5.dp)
                .height(40.dp)
                .fillMaxSize()
        )
        {
            Text("Применить цвет")
        }

        if (errorMessage.value.isNotEmpty()) {
            Text(
                text = errorMessage.value,
                color = Color.Red,
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column()
        {
            colorsMap.forEach { (colorName, colorValue) ->
                Button(
                    {
                        message.value = colorName
                        errorMessage.value = ""
                    },
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorValue
                    ),
                    modifier = Modifier
                        .padding(0.dp, 0.dp, 0.dp, 5.dp)
                        .height(40.dp)
                        .fillMaxSize()
                )
                {
                    Text(colorName)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PracticeTheme {
        MyApp()
    }
}