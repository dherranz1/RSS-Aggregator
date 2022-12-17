package com.dherranz1.rss_aggregator.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dherranz1.rss_aggregator.R
import com.dherranz1.rss_aggregator.domain.GetSourceRssUseCase

class RssFeedAdapter(private val dataSet: List<GetSourceRssUseCase.SourceRssResponse>) :
    RecyclerView.Adapter<RssFeedAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val rssName: TextView
        val rssDescription: TextView

        init {
            rssName = view.findViewById(R.id.rss_name)
            rssDescription = view.findViewById(R.id.rss_description)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.rss_feed_item, viewGroup, false)
        )

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.rssName.text = dataSet[position].name
        viewHolder.rssDescription.text = dataSet[position].url
    }

    override fun getItemCount() = dataSet.size
}