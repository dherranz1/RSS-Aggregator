package com.dherranz1.rss_aggregator.domain

class SaveSourceRssUseCase(private val repository : SourceRssRepository) {
    fun execute(name : String, url : String) =
        repository.create(name, url)
}