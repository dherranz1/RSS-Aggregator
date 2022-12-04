package com.dherranz1.rss_aggregator.domain

import com.dherranz1.app.domain.ErrorApp
import com.dherranz1.app.functional.Either

interface SourceRssRepository {
    fun create(name : String, url : String)
    fun getAllSources() : List<SourceRss>
    fun delete(id : String) : Either<ErrorApp, String>
}