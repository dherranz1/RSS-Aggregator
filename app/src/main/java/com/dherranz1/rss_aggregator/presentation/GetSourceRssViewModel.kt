package com.dherranz1.rss_aggregator.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dherranz1.rss_aggregator.domain.GetSourceRssUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GetSourceRssViewModel(private val getSourceRssUseCase: GetSourceRssUseCase) : ViewModel() {

    val rssFeedPublisher = MutableLiveData<RssUiState>()

    fun getRssList(){
        viewModelScope.launch(Dispatchers.IO) {
            val rssList = getSourceRssUseCase.execute()
            rssFeedPublisher.postValue(
                RssUiState(
                    isLoading = false,
                    rssList = rssList
                )
            )
        }
    }

    data class RssUiState(
        val isLoading : Boolean = true,
        val rssList : List<GetSourceRssUseCase.SourceRssResponse>
    )
}