package com.dherranz1.rss_aggregator.data.local

import com.dherranz1.rss_aggregator.domain.SourceRss

interface LocalDataSource {
    fun save(name : String, url : String)
    fun getAllSources() : List<SourceRss>
}