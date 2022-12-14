package com.dherranz1.rss_aggregator.data.local.xml

import android.content.SharedPreferences
import android.util.Log
import com.dherranz1.app.domain.ErrorApp
import com.dherranz1.app.functional.Either
import com.dherranz1.app.functional.left
import com.dherranz1.app.functional.right
import com.dherranz1.rss_aggregator.data.local.LocalDataSource
import com.dherranz1.rss_aggregator.domain.SourceRss

class XmlLocalDataSource(private val sharedPreferences: SharedPreferences) : LocalDataSource {

    private val editor = sharedPreferences.edit()

    override fun save(name: String, url: String) =
        editor.putString(url,name).apply()


    override fun getAllSources(): List<SourceRss> =
        sharedPreferences.all.map { rss ->
            SourceRss(
                name = rss.value.toString(),
                url = rss.key.toString()
            )
        }

    override fun delete(id: String): Either<ErrorApp, String> {
        editor.remove(id).apply()

        val result = sharedPreferences.getString(id,null)

        return if(result == null)
            id.right()
        else
            ErrorApp.DataError().left()
    }
}