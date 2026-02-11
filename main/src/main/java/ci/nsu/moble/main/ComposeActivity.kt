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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.mutableStateSetOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Button

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
    //"Orange" to Orange,
    "Yellow" to Yellow,
    "Green" to Green,
    "Blue" to Blue
    //"Indigo" to Indigo,
    //"violet" to Violet)
)


@Composable
fun MyApp() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(60.dp),
        verticalArrangement = Arrangement.Top
        
    ) {
        val message = remember { mutableStateOf("") }
        Column {
            //Text(message.value, fontSize = 28.sp)
            TextField(
                value = message.value,
                textStyle = TextStyle(fontSize = 25.sp),
                placeholder = {Text("Введите цвет")},
                onValueChange = {newText -> message.value = newText}
            )
        }
        Button({})
        {
            Text("Применить цвет")

        }
        Spacer(modifier = Modifier.height(16.dp))
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Button({},
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color()
                )
            )
            {
                Text("Red")
            }

            Button({},
                shape = RectangleShape)
            {
                Text("Orange")
            }

            Button({},
                shape = RectangleShape)
            {
                Text("Yellow")
            }

            Button({},
                shape = RectangleShape)
            {
                Text("Green")
            }

            Button({},
                shape = RectangleShape)
            {
                Text("Blue")
            }

            Button({},
                shape = RectangleShape)
            {
                Text("Violet")
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