package com.dherranz1.rss_aggregator.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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

    }

    private fun setUpCancelButton(view: View){
        view.findViewById<Button>(R.id.form_rss_button_cancel).setOnClickListener{
            this.dismiss()
        }
    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }
}