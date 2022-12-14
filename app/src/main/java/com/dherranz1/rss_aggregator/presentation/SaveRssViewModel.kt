package com.dherranz1.rss_aggregator.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dherranz1.rss_aggregator.domain.SaveSourceRssUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SaveRssViewModel(private val saveSourceRssUseCase : SaveSourceRssUseCase) : ViewModel() {

    //Publisher
    /*val rssFeedPublisher: MutableLiveData<RssUiState> by lazy {
        MutableLiveData<RssUiState>()
    }*/

    var rssFeedPublisher: MutableLiveData<RssUiState> =
        MutableLiveData<RssUiState>()


    fun saveSourceRss(name : String, url : String){

        viewModelScope.launch(Dispatchers.IO) {
            saveSourceRssUseCase.execute(name, url)

            rssFeedPublisher.postValue(
                RssUiState(
                    isSuccess = true
                )
            )
        }
    }


    data class RssUiState(
        val isSuccess : Boolean = false
    )
}