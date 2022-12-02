package com.dherranz1.rss_aggregator.data.remote.retrofit

import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

class ApiClient(private val urlweb : String) {
    val baseUrl : String = urlweb
    
    private val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl("https://google.es/")
        .addConverterFactory(SimpleXmlConverterFactory.create())
        .build()

    fun getInstance() = retrofit
}