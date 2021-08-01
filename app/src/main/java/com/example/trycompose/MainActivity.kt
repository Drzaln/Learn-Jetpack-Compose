package com.example.trycompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trycompose.ui.theme.TryComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TryComposeTheme {
               MainApp()
            }
        }
    }
}

@Composable
fun MainApp() {
    val counterValue = remember { mutableStateOf(0) }
    val (text, setText) = remember {mutableStateOf("")}
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text="Try Jetpack Compose")
                },
                elevation = 4.dp
            )
        }
    ){
        Column(modifier = Modifier
            .fillMaxHeight()
            .padding(all = 16.dp)) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "Button clicked ${counterValue.value} times")
                Spacer(modifier = Modifier.height(8.dp))
                TextField(value = text, onValueChange = setText)
            }
            Spacer(modifier = Modifier.height(8.dp))
            ButtonCounter(
                count = counterValue.value,
                updateCount = {newCount ->
                    counterValue.value = newCount
                }
            )
        }
    }
}

@Composable
fun ButtonCounter(count: Int, updateCount: (Int) -> Unit) {
    Button(onClick = {updateCount(count+1)}) {
        Text(text = "Increment")
    }
}

@Preview
@Composable
fun PreviewMessageCard() {
    TryComposeTheme{
        MainApp()
    }
}
