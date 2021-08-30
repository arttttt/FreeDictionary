package com.arttttt.dictionary.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arttttt.dagger2.PerScreen
import com.arttttt.dictionary.data.api.WordsApi
import com.arttttt.dictionary.data.repository.WordsRepositoryImpl
import com.arttttt.dictionary.domain.repository.WordsRepository
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import timber.log.Timber

@Module
private abstract class MainModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        @PerScreen
        fun provideRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://api.dictionaryapi.dev/api/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
        }

        @JvmStatic
        @Provides
        @PerScreen
        fun provideWordsApi(retrofit: Retrofit): WordsApi {
            return retrofit.create()
        }

    }

    @Binds
    @PerScreen
    abstract fun provideWordsRepository(impl: WordsRepositoryImpl): WordsRepository

}

@PerScreen
@Component(
    modules = [
        MainModule::class
    ]
)
private interface MainScreenComponent {

    @Component.Factory
    interface Factory {
        fun create(): MainScreenComponent
    }

    val wordsApi: WordsApi
    val wordsRepository: WordsRepository

}

@Preview(
    showBackground = true
)
@Composable
fun MainScreen() {
    val component = remember {
        DaggerMainScreenComponent
            .factory()
            .create()
    }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.padding(
            all = 16.dp
        )
    ) {
        SearchWordInput(
            wordsApi = component.wordsApi
        )
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        SearchHistoryList()
    }
}

@Composable
private fun SearchWordInput(
    wordsApi: WordsApi
) {
    val scope = rememberCoroutineScope()

    val inputState = remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = inputState.value,
        onValueChange = { text ->
            inputState.value = text

            scope.launch {
                val word = wordsApi.getWord(text)

                Timber.e(word.toString())
            }
        }
    )
}

@Composable
private fun SearchHistoryList() {
    LazyColumn(
        content = {
            repeat(10) {
                item {
                    Text(
                        text = it.toString()
                    )
                }
            }
        }
    )
}
