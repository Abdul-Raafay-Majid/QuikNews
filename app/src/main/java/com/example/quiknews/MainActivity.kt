package com.example.quiknews

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.quiknews.presentation.NewsWire.NewsWireScreen
import com.example.quiknews.presentation.NewsWireEvent
import com.example.quiknews.presentation.NewsWireViewModel
import com.example.quiknews.ui.theme.QuikNewsTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: NewsWireViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            this.setKeepOnScreenCondition{
                viewModel.isClearing.value
            }
        }
        setContent {
            Log.d("Clean Up", "onDestroy")
            QuikNewsTheme {
                // A surface container using the 'background' color from the theme
                Box(
                    modifier= Modifier.fillMaxSize()
                ) {
                    NewsWireScreen()
                }
            }
        }
    }


}


@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    QuikNewsTheme {
        Greeting("Android")
    }
}