package com.dherranz1.rss_aggregator.data

import com.dherranz1.rss_aggregator.data.local.LocalDataSource
import com.dherranz1.rss_aggregator.domain.SourceRssRepository

class SourceRssDataRepository(private val localDataSource : LocalDataSource) : SourceRssRepository {
    override fun create(name: String, url: String) {
        localDataSource.save(name,url)
    }
}