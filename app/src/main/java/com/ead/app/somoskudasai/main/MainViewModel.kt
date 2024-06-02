package com.ead.app.somoskudasai.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ead.lib.somoskudasai.SomosKudasai
import com.ead.lib.somoskudasai.models.News
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    /**
     * news from loading page of SomosKudasai
     */
    private val _news : MutableState<News?> = mutableStateOf(null)
    val news : State<News?> = _news

    /**
     * Handling event to load from home page
     * and load the first recent news page
     * with details
     */
    fun onEvent() {
        viewModelScope.launch (Dispatchers.IO) {

            val home = SomosKudasai.getHome()

            val recentNews  = home?.recentNews ?: return@launch

            val firstNews = recentNews.first()

            val newsDetail = SomosKudasai.getNewsInfo(firstNews.url)
            _news.value = newsDetail

        }
    }
}