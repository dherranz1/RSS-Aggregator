package com.dherranz1.rss_aggregator.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import com.dherranz1.rss_aggregator.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class RssManagementFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rss_managment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val modalBottomSheet = BottomSheetDialog()

        val addButton = view.findViewById<ImageView>(R.id.toolbar_manage_button_add)

        val modalBottomSheetBehavior =
        // Use this to programmatically apply behavior attributes

        addButton.setOnClickListener {
            modalBottomSheet.setStyle(DialogFragment.STYLE_NORMAL,R.style.DialogStyle)
            modalBottomSheet.show(childFragmentManager, BottomSheetDialog.TAG)
        }

    }
}