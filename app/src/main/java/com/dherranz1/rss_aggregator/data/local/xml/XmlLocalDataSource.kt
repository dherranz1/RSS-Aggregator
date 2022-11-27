package com.dherranz1.rss_aggregator.data.local.xml

import android.content.SharedPreferences
import com.dherranz1.rss_aggregator.data.local.LocalDataSource
import com.dherranz1.rss_aggregator.domain.SourceRss

class XmlLocalDataSource(private val sharedPreferences: SharedPreferences) : LocalDataSource {

    private val editor = sharedPreferences.edit()

    override fun save(name: String, url: String) {
        editor.putString(url,name).apply()
    }

    override fun getAllSources(): List<SourceRss> =
        sharedPreferences.all.map { rss ->
            SourceRss(
                name = rss.value.toString(),
                url = rss.key.toString()
            )
        }
}