package com.dherranz1.rss_aggregator.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dherranz1.app.DataChangesNotifier
import com.dherranz1.app.RepositoryObserver
import com.dherranz1.rss_aggregator.R
import com.dherranz1.rss_aggregator.domain.GetSourceRssUseCase
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar

class RssManagementFragment : Fragment(), RepositoryObserver {

    private var recyclerView : RecyclerView? = null
    private var adapter : RssManagementAdapter? = null

    private val viewModel : GetSourceRssViewModel by lazy {
        RssFactory().getSourceRssViewModel(requireContext())
    }
    private val deleteViewModel : DeleteSourceRssViewModel by lazy {
        RssFactory().getDeleteRssViewModel(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_rss_managment, container, false)
        recyclerView = view.findViewById(R.id.rss_manage_list)
        recyclerView?.layoutManager = LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpAddButton(view)
        setUpObservers()
        updateContent()

        DataChangesNotifier.subscribe(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        DataChangesNotifier.unSubscribe(this)
    }

    private fun setUpAddButton(view : View){

        val addButton = view.findViewById<ImageView>(R.id.toolbar_manage_button_add)

        addButton.setOnClickListener {
            getBottomSheetDialog().show(childFragmentManager, BottomSheetDialog.TAG)
        }
    }

    private fun getBottomSheetDialog() : BottomSheetDialogFragment{
        val modalBottomSheet = BottomSheetDialog()
        modalBottomSheet.setStyle(DialogFragment.STYLE_NORMAL,R.style.DialogStyle)

        return modalBottomSheet
    }

    private fun setUpObservers() {
        val rssFeedSubscriber =
            Observer<GetSourceRssViewModel.RssUiState> { uiState ->
                if (!uiState.isLoading)
                    loadRssList(uiState.rssList)
            }

        val rssDeleteSubscriber =
            Observer<DeleteSourceRssViewModel.RssUiState> { uiState ->
                if (uiState.isSuccess)
                    showSnackbar(getText(R.string.snackbar_rss_deleted).toString())
                else
                    showSnackbar(getText(R.string.snackbar_error).toString())
            }

        viewModel.rssFeedPublisher.observe(viewLifecycleOwner, rssFeedSubscriber)
        deleteViewModel.rssDeletePublisher.observe(viewLifecycleOwner, rssDeleteSubscriber)
    }

    private fun loadRssList(rssList : List<GetSourceRssUseCase.SourceRssResponse>){
        adapter = RssManagementAdapter(rssList)
        adapter!!.deleteFunction = {
            url -> deleteViewModel.deleteSourceRss(url)
        }
        recyclerView?.adapter = adapter
    }

    private fun updateContent() =
        viewModel.getRssList()


    override fun notifyChanges() =
        updateContent()


    private fun showSnackbar(message : String) =
        this.view?.let { view ->
            Snackbar.make(view,message, Snackbar.LENGTH_SHORT).show()
        }
}