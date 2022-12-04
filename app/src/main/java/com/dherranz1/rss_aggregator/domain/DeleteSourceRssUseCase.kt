package com.dherranz1.rss_aggregator.domain

import com.dherranz1.app.domain.ErrorApp
import com.dherranz1.app.functional.Either

class DeleteSourceRssUseCase(private val repository: SourceRssRepository) {
    fun execute(id : String) : Either<ErrorApp, String> =
        repository.delete(id)

}