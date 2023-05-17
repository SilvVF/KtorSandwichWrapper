package io.silv.ktorsandwhich

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.silv.ktorsandwich.client.KSandwichClient
import io.silv.ktorsandwich.client.get
import io.silv.ktorsandwhich.ui.theme.KtorSandwhichTheme
import io.silv.ktorsandwich.message
import io.silv.ktorsandwich.suspendOnFailure
import io.silv.ktorsandwich.suspendOnSuccess
import kotlinx.coroutines.launch


val client: KSandwichClient = KSandwichClient.create()

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val scope = rememberCoroutineScope()
            var text by remember {
                mutableStateOf("")
            }
            val scrollState = rememberScrollState()

            KtorSandwhichTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        Modifier.fillMaxSize().verticalScroll(scrollState)
                    ) {
                        Button(
                            onClick = {
                                scope.launch {
                                    client.get<String>("https://jsonplaceholder.typicode.com/users")
                                        .suspendOnSuccess {
                                            text = data
                                        }
                                        .suspendOnFailure {
                                            text = message()
                                        }
                                }
                            }
                        ) { Text(text = "fetch data") }
                        Text(text)
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KtorSandwhichTheme {
        Greeting("Android")
    }
}