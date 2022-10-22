package com.example.quiknews.presentation

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quiknews.R
import com.example.quiknews.core.utils.Resource
import com.example.quiknews.data.NewsRepoImpl
import com.example.quiknews.domain.NewsWireUseCase
import com.example.quiknews.presentation.utils.Section
import com.example.quiknews.presentation.utils.Sections
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Delay
import kotlinx.coroutines.delay
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
        getNewsWireUseCases(NewsWireEvent.GetArticles(1))
    }

     fun getNewsWireUseCases(newsWireEvent: NewsWireEvent) {
        when (newsWireEvent) {
            is NewsWireEvent.GetArticles -> {
                viewModelScope.launch {
                    if(_newsWireState.value.newsWireArticles!=null)
                        _newsWireState.value=newsWireState.value.copy(
                            newsWireArticles = null,
                            isLoading = true,
                            section ="",
                            error = null
                        )
                    delay(500L)
                    newsWireUseCase.getNewsWireData("all", Sections.sections[newsWireEvent.page].pathParam)
                        .onEach { result ->
                            when (result) {
                                is Resource.Success -> {
                                    this@NewsWireViewModel.newsWireState.value = this@NewsWireViewModel.newsWireState.value.copy(
                                        newsWireArticles =result.data?.articles,
                                        isLoading = false,
                                        section = result.data?.section
                                    )
                                }

                                is Resource.Loading -> {
                                    this@NewsWireViewModel.newsWireState.value = this@NewsWireViewModel.newsWireState.value.copy(
                                        newsWireArticles = result.data?.articles,
                                        isLoading = true,
                                        section = result.data?.section
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

            is NewsWireEvent.RefreshArticles->{
                viewModelScope.launch {
                    if(_newsWireState.value.newsWireArticles!=null)
                        _newsWireState.value=newsWireState.value.copy(
                            newsWireArticles = null,
                            isLoading = true,
                            section ="",
                            error = null
                        )
                    newsWireUseCase.refreshArticle("all",newsWireEvent.section).onEach { result->
                        when(result){
                            is Resource.Loading->{
                                this@NewsWireViewModel.newsWireState.value = this@NewsWireViewModel.newsWireState.value.copy(
                                    isLoading = true,
                                    section = result.data?.section
                                )
                            }

                            is Resource.Error->{
                                this@NewsWireViewModel.newsWireState.value = this@NewsWireViewModel.newsWireState.value.copy(
                                    error = result.message
                                )
                            }

                            is Resource.Success -> {
                                this@NewsWireViewModel.newsWireState.value = this@NewsWireViewModel.newsWireState.value.copy(
                                    newsWireArticles =result.data?.articles,
                                    isLoading = false,
                                    section = result.data?.section
                                )
                            }
                        }
                    }.launchIn(this)
                }
            }

        }

    }

    fun launchArticleInBrowser(context: Context,link:String){
        viewModelScope.launch {
                val intent=Intent(Intent.ACTION_VIEW).apply {
                    setData(Uri.parse(link))
                }
            context.startActivity(intent)
        }
    }

    fun shareNewsArticle(context:Context,link:String){
        viewModelScope.launch {
            val intent=Intent(Intent.ACTION_SEND).apply {
                type="text/html"
                putExtra(Intent.EXTRA_TEXT,link)
            }
            context.startActivity(Intent.createChooser(intent,context.getString(R.string.share_txt)))
        }

    }
}

