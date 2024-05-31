package com.ead.app.somoskudasai.main

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ead.lib.somoskudasai.SomosKudasai
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _result : MutableState<String?> = mutableStateOf(null)
    val result : State<String?> = _result

    fun onEvent() {
        viewModelScope.launch (Dispatchers.IO) {
            val pageDetails = SomosKudasai.getNewsInfo("https://somoskudasai.com/noticias/cultura-otaku/whisper-me-a-love-song-es-la-peor-produccion-de-la-temporada")
            _result.value = pageDetails?.structure?.map { it.value }.toString()

            pageDetails?.structure?.forEach {
                Log.d("test", "onEvent: ${it.value}")
            }
        }
    }
}