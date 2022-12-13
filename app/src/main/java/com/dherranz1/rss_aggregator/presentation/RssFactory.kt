package com.dherranz1.rss_aggregator.presentation

import android.content.Context
import android.content.SharedPreferences
import com.dherranz1.rss_aggregator.data.SourceRssDataRepository
import com.dherranz1.rss_aggregator.data.local.LocalDataSource
import com.dherranz1.rss_aggregator.data.local.xml.XmlLocalDataSource
import com.dherranz1.rss_aggregator.domain.SaveSourceRssUseCase
import com.dherranz1.rss_aggregator.domain.SourceRssRepository

class RssFactory {

    fun getSaveRssViewModel(context: Context) :SaveRssViewModel =
        SaveRssViewModel(getSaveSourceRssUseCase(getSharedPreferences(context)))

    private fun getSaveSourceRssUseCase(sharedPreferences: SharedPreferences) : SaveSourceRssUseCase =
        SaveSourceRssUseCase(getSourceRssRepository(sharedPreferences))

    private fun getSourceRssRepository(sharedPreferences: SharedPreferences) : SourceRssRepository =
        SourceRssDataRepository(getLocalDataSource(sharedPreferences))

    private fun getLocalDataSource(sharedPreferences: SharedPreferences) : LocalDataSource =
        XmlLocalDataSource(sharedPreferences)

    private fun getSharedPreferences(context: Context) =
        context.getSharedPreferences("rss", Context.MODE_PRIVATE)

}