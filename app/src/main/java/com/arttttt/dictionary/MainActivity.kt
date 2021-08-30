package com.arttttt.dictionary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.arttttt.dictionary.data.api.WordsApi
import com.arttttt.dictionary.ui.MainScreen
import com.arttttt.dictionary.ui.theme.DictionaryTheme
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DictionaryTheme {
                Scaffold(
                    topBar = {
                        TopAppBar {
                            Text(
                                text = stringResource(
                                    id = R.string.app_name
                                )
                            )
                        }
                    }
                ) {
                    MainScreen()
                }
            }
        }
    }
}
