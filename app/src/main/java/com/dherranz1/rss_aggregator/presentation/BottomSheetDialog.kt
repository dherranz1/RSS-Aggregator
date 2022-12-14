package com.dherranz1.rss_aggregator.presentation

import android.content.Context
import android.os.Bundle
import android.os.Message
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import com.dherranz1.rss_aggregator.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar


class BottomSheetDialog : BottomSheetDialogFragment() {

    private val saveRssViewModel : SaveRssViewModel by lazy {
        RssFactory().getSaveRssViewModel(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bottom_sheet_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        setUpCancelButton(view)
        setUpSaveButton(view)
    }

    private fun setUpCancelButton(view: View){
        view.findViewById<Button>(R.id.form_rss_button_cancel).setOnClickListener{
            this.dismiss()
        }
    }

    private fun setUpSaveButton(view: View){
        view.findViewById<Button>(R.id.form_rss_button_save).setOnClickListener{

            val inputName = view.findViewById<EditText>(R.id.form_rss_input_name)
            val inputUrl = view.findViewById<EditText>(R.id.form_rss_input_url)

            saveRssViewModel.saveSourceRss(inputName.text.toString(),inputUrl.text.toString())

            inputName.setText("")
            inputUrl.setText("")
        }
    }

    private fun setupObservers() {
        val rssFeedSubscriber =
            Observer<SaveRssViewModel.RssUiState> { uiState ->
                if (uiState.isSuccess)
                    showSnackbar(getText(R.string.snackbar_success).toString())
                else
                    showSnackbar(getText(R.string.snackbar_error).toString())

            }
        saveRssViewModel.rssFeedPublisher.observe(viewLifecycleOwner, rssFeedSubscriber)
    }

    private fun showSnackbar(message: String) =
        this.view?.let { view ->
            Snackbar.make(view,message, Snackbar.LENGTH_SHORT).show()
        }

    companion object {
        const val TAG = "ModalBottomSheet"
    }
}