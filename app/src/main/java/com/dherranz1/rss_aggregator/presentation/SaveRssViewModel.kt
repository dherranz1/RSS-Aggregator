package com.dherranz1.rss_aggregator.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dherranz1.rss_aggregator.domain.SaveSourceRssUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SaveRssViewModel(private val saveSourceRssUseCase : SaveSourceRssUseCase) : ViewModel() {

    fun saveSourceRss(name : String, url : String){
        viewModelScope.launch(Dispatchers.IO) {

        }
    }
}