package com.dherranz1.rss_aggregator.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dherranz1.rss_aggregator.R
import com.dherranz1.rss_aggregator.domain.GetSourceRssUseCase

class RssManagementAdapter(private val dataSet: List<GetSourceRssUseCase.SourceRssResponse>) :
    RecyclerView.Adapter<RssManagementAdapter.ViewHolder>() {

    var deleteFunction: ((String) -> Unit)? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val rssName: TextView
        val rssDescription: TextView
        val rssDelete : ImageView

        init {
            rssName = view.findViewById(R.id.rss_name)
            rssDescription = view.findViewById(R.id.rss_description)
            rssDelete = view.findViewById(R.id.rss_img_delete)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.rss_management_item, viewGroup, false)
        )

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.rssName.text = dataSet[position].name
        viewHolder.rssDescription.text = dataSet[position].url
        viewHolder.rssDelete.setOnClickListener {
            deleteFunction?.invoke(viewHolder.rssDescription.text.toString())
        }
    }

    override fun getItemCount() = dataSet.size
}