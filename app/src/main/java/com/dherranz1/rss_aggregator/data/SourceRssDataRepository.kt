package com.dherranz1.rss_aggregator.data

import com.dherranz1.app.DataChangesNotifier
import com.dherranz1.app.domain.ErrorApp
import com.dherranz1.app.functional.Either
import com.dherranz1.rss_aggregator.data.local.LocalDataSource
import com.dherranz1.rss_aggregator.domain.SourceRss
import com.dherranz1.rss_aggregator.domain.SourceRssRepository

class SourceRssDataRepository(private val localDataSource : LocalDataSource) : SourceRssRepository {
    override fun create(name: String, url: String){
        localDataSource.save(name,url)
        sendNotification()
    }


    override fun getAllSources(): List<SourceRss> =
        localDataSource.getAllSources()

    override fun delete(id: String): Either<ErrorApp, String> =
        localDataSource.delete(id)

    private fun sendNotification() =
        DataChangesNotifier.notifyNewChanges()


}