package com.dherranz1.rss_aggregator.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dherranz1.app.DataChangesNotifier
import com.dherranz1.rss_aggregator.R
import com.dherranz1.rss_aggregator.domain.GetSourceRssUseCase

class RssFeedFragment : Fragment() {

    private var recyclerView : RecyclerView? = null
    private var adapter : RssFeedAdapter? = null

    private val viewModel : GetSourceRssViewModel by lazy {
        RssFactory().getSourceRssViewModel(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_rss_feed, container, false)
        recyclerView = view.findViewById(R.id.rss_feed_list)
        recyclerView?.layoutManager = LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpObservers()
        updateContent()
    }

    private fun setUpObservers() {
        val rssFeedSubscriber =
            Observer<GetSourceRssViewModel.RssUiState> { uiState ->
                if (!uiState.isLoading)
                    loadRssList(uiState.rssList)
            }
        viewModel.rssFeedPublisher.observe(viewLifecycleOwner, rssFeedSubscriber)
    }

    private fun loadRssList(rssList : List<GetSourceRssUseCase.SourceRssResponse>){
        adapter = RssFeedAdapter(rssList)
        recyclerView?.adapter = adapter
    }

    private fun updateContent(){
        viewModel.getRssList()
    }
}