package com.dherranz1.rss_aggregator.data.remote.retrofit

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url


interface RssService {

    @GET
    suspend fun getAll(@Url url:String): Response<RssApiModel?>
}