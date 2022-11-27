package com.dherranz1.rss_aggregator.domain

interface SourceRssRepository {
    fun create(name : String, url : String)
    fun getAllSources() : List<SourceRss>
}