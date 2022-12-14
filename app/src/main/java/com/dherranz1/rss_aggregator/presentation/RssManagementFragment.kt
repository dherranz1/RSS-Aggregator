package com.dherranz1.rss_aggregator.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.dherranz1.app.DataChangesNotifier
import com.dherranz1.app.RepositoryObserver
import com.dherranz1.rss_aggregator.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RssManagementFragment : Fragment(), RepositoryObserver {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_rss_managment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val modalBottomSheet = BottomSheetDialog()
        modalBottomSheet.setStyle(DialogFragment.STYLE_NORMAL,R.style.DialogStyle)

        val addButton = view.findViewById<ImageView>(R.id.toolbar_manage_button_add)

        addButton.setOnClickListener {
            modalBottomSheet.show(childFragmentManager, BottomSheetDialog.TAG)
        }

        DataChangesNotifier.subscribe(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        DataChangesNotifier.unSubscribe(this)
    }

    override fun notifyChanges(){
        updateContent()
        showSnackbar()
    }

    private fun updateContent(){
        this.view?.let { view ->
            activity?.runOnUiThread {
                view.findViewById<TextView>(R.id.example_text_management).text = getText(R.string.snackbar_content_updated)
            }
        }
    }

    private fun showSnackbar() =
        this.view?.let { view ->
            Snackbar.make(view,getText(R.string.snackbar_content_updated), Snackbar.LENGTH_SHORT).show()
        }
}