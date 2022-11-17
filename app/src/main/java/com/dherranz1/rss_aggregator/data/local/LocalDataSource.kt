package com.dherranz1.rss_aggregator.data.local

interface LocalDataSource {
    fun save(name : String, url : String)
}