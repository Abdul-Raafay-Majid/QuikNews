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

    private val _newsWireState = mutableStateOf(NewsWireState())
    val newsWireState= this._newsWireState



    init {
        getNewsWireUseCases(NewsWireEvent.GetArticles("all", "all"))
    }

     fun getNewsWireUseCases(newsWireEvent: NewsWireEvent) {
        when (newsWireEvent) {
            is NewsWireEvent.GetArticles -> {
                viewModelScope.launch {
                    if(_newsWireState.value.newsWireArticles!=null)
                    _newsWireState.value=newsWireState.value.copy(
                        newsWireArticles = null,
                        isLoading = true,
                    )
                    newsWireUseCase.getNewsWireData(newsWireEvent.source, newsWireEvent.section)
                        .onEach { result ->
                            when (result) {
                                is Resource.Success -> {
                                    this@NewsWireViewModel.newsWireState.value = this@NewsWireViewModel.newsWireState.value.copy(
                                        newsWireArticles = result.data,
                                        isLoading = false
                                    )
                                }

                                is Resource.Loading -> {
                                    this@NewsWireViewModel.newsWireState.value = this@NewsWireViewModel.newsWireState.value.copy(
                                        newsWireArticles = result.data,
                                        isLoading = true,
                                    )
                                }

                                is Resource.Error -> {
                                    this@NewsWireViewModel.newsWireState.value = this@NewsWireViewModel.newsWireState.value.copy(
                                        error = result.message
                                    )
                                }
                            }
                        }.launchIn(this)
                }
            }

            is NewsWireEvent.ClearAllArticles->{
                viewModelScope.launch {
                    newsWireUseCase.deleteAllArticles()
                }
            }
        }

    }
}

