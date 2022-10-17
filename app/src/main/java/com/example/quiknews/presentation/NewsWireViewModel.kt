package com.example.quiknews.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quiknews.core.utils.Resource
import com.example.quiknews.data.NewsRepoImpl
import com.example.quiknews.domain.NewsWireUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsWireViewModel @Inject constructor(
    private val newsWireUseCase: NewsWireUseCase,
    private val newsRepo:NewsRepoImpl
) :
    ViewModel() {

    val newsWireState = mutableStateOf(NewsWireState())

    init {
        getNewsWireUseCases(NewsWireEvent.GetArticles("all", "all"))
    }

     fun getNewsWireUseCases(newsWireEvent: NewsWireEvent) {
        when (newsWireEvent) {
            is NewsWireEvent.GetArticles -> {
                viewModelScope.launch {
                    newsWireUseCase.getNewsWireData(newsWireEvent.source, newsWireEvent.section)
                        .onEach { result ->
                            when (result) {
                                is Resource.Success -> {
                                    newsWireState.value = newsWireState.value.copy(
                                        newsWireDto = result.data,
                                        isLoading = false
                                    )
                                }

                                is Resource.Loading -> {
                                    newsWireState.value = newsWireState.value.copy(
                                        isLoading = true
                                    )
                                }

                                is Resource.Error -> {
                                    newsWireState.value = newsWireState.value.copy(
                                        error = result.message
                                    )
                                }
                            }
                        }.launchIn(this)
                }
            }
        }

    }
}

