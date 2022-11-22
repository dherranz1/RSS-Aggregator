package com.dherranz1.rss_aggregator.domain

class DeleteSourceRssUseCase(private val repository: SourceRssRepository) {
    fun execute(id : String) : Either<ErrorApp,String>{
        val result = repository.delete(id)

        return if (result.isLeft())
            ErrorApp.DataError().left()
        else{
            result.get().right()
        }
    }
}