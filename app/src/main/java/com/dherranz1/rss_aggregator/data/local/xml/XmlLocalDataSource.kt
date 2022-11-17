package com.dherranz1.rss_aggregator.data.local.xml

import android.content.SharedPreferences
import com.dherranz1.rss_aggregator.data.local.LocalDataSource

class XmlLocalDataSource(private val sharedPreferences: SharedPreferences) : LocalDataSource {

    val editor = sharedPreferences.edit()

    override fun save(name: String, url: String) {
        editor.putString(url,name).apply()
    }
}