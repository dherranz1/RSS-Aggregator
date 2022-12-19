package com.dherranz1.rss_aggregator.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dherranz1.app.domain.ErrorApp
import com.dherranz1.app.functional.Either
import com.dherranz1.rss_aggregator.domain.DeleteSourceRssUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DeleteSourceRssViewModel(private val deleteSourceRssUseCase: DeleteSourceRssUseCase) : ViewModel(){

    val rssDeletePublisher = MutableLiveData<RssUiState>()

    fun deleteSourceRss(url : String) =
        viewModelScope.launch(Dispatchers.IO) {
            val result : Either<ErrorApp, String> = deleteSourceRssUseCase.execute(url)
            var rssUiState = RssUiState(false)

            if(result.isRight()) rssUiState = RssUiState(isSuccess = true)

            rssDeletePublisher.postValue(
                rssUiState
            )
        }

    data class RssUiState(
        val isSuccess : Boolean = false
    )
}