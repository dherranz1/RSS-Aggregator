package com.dherranz1.rss_aggregator.data.remote.retrofit


class RssRemoteDataSource(private val apiClient : ApiClient)  {

    private var service: RssService = apiClient.getInstance().create(RssService::class.java)

    suspend fun getAll(): RssApiModel? {
        return service.getAll(apiClient.baseUrl).body()
    }


}