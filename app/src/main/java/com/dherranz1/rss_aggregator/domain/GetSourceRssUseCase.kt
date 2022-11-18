package com.dherranz1.rss_aggregator.domain

class GetSourceRssUseCase(private val repository: SourceRssRepository) {
    fun execute() : List<SourceRssResponse> =
        repository.getAllSources().map { rss ->
            SourceRssResponse(
                name = rss.name,
                url = rss.url
            )
        }

    data class SourceRssResponse(
        val name : String,
        val url : String
    )
}