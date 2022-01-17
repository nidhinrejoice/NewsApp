package com.nidhin.newsapp.homescreen.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nidhin.newsapp.commons.Resource
import com.nidhin.newsapp.commons.UiStateViewModel
import com.nidhin.newsapp.domain.usecase.homescreen.GetTopHeadlines
import com.nidhin.newsapp.presentation.homescreen.HomeScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getTopHeadlinesUseCase: GetTopHeadlines
) : UiStateViewModel() {

//    private val _topHeadlines: MutableLiveData<List<ArticleDto>> = MutableLiveData()
//    val topHeadlines: LiveData<List<ArticleDto>>
//        get() = _topHeadlines
//    private val _catWiseArticles: MutableLiveData<List<ArticleDto>> = MutableLiveData()
//    val catWiseArticles: LiveData<List<ArticleDto>>
//        get() = _catWiseArticles

    private val _state = mutableStateOf(HomeScreenState())
    val state: State<HomeScreenState> = _state


    init {
        getTopHeadlines()
    }


    fun getTopHeadlines() {
        viewModelScope.launch(handler) {
            getTopHeadlinesUseCase("").onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = HomeScreenState(
                            topHeadlines = result.data ?: emptyList(),
                            selectedCategory = "business"
                        )
                        state.value.selectedCategory?.let { getCategoryWiseHeadlines(it) }
                    }
                    is Resource.Error -> {
                        _state.value =
                            HomeScreenState(error = result.message ?: "Something went wrong")
                    }
                    is Resource.Loading -> {
                        _state.value = HomeScreenState(isLoading = true)
                    }
                }
            }.collect {value ->  {
                value.message?.let { Log.d("", it) }
            }}
        }
    }

    fun getCategoryWiseHeadlines(category: String) {
        viewModelScope.launch(handler) {
            getTopHeadlinesUseCase(category).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            topCategoryHeadlines = result.data ?: emptyList(),
                            selectedCategory = category, isLoading = false
                        )
                    }
                    is Resource.Error -> {
                        _state.value =
                            _state.value.copy(error = result.message ?: "Something went wrong", isLoading = false)
                    }
                    is Resource.Loading -> {
                        _state.value = _state.value.copy(
                            isLoading = true
                        )
                    }
                }
            }.collect { }
        }
    }

    override fun onDestroy(lifecycleOwner: LifecycleOwner) {
        TODO("Not yet implemented")
    }

}
