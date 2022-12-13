package com.dherranz1.rss_aggregator.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.dherranz1.rss_aggregator.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class BottomSheetDialog : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bottom_sheet_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

            val saveRssViewModel : SaveRssViewModel =
                 RssFactory().getSaveRssViewModel(requireContext())

            saveRssViewModel.saveSourceRss(inputName.text.toString(),inputUrl.text.toString())
            Log.d("@dev","save clicked")
        }
    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }
}