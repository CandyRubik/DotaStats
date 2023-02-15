package ru.rubik.dotastats.presentation.dialogs

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import ru.rubik.dotastats.presentation.R
import ru.rubik.dotastats.presentation.ui.ProgressBaseFragment

class DialogFactory(
    private val fragment: ProgressBaseFragment
) {

    fun createLoaderFragment(): AlertDialog? {
        return AlertDialog.Builder(fragment.requireActivity()).apply {
            val view = LayoutInflater.from(fragment.requireActivity())
                .inflate(R.layout.dialog_loading, null)
            setView(view)
        }.create().apply {
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setCancelable(false)
            show()
            window?.setLayout(200, 200)
        }
    }

    fun createNetworkErrorDialog(onPositiveButtonClickListener: () -> Unit): AlertDialog? {
        return AlertDialog.Builder(fragment.requireActivity()).apply {
            val view =
                LayoutInflater.from(fragment.requireActivity())
                    .inflate(R.layout.dialog_network_error, null)
            setView(view)
            setPositiveButton(fragment.getString(R.string.dialog_error_positive_button)) { _, _ ->
                onPositiveButtonClickListener()
            }
        }.create().apply {
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setCancelable(false)
            show()
        }
    }

    fun createOtherErrorDialog(onPositiveButtonClickListener: () -> Unit): AlertDialog? {
        return AlertDialog.Builder(fragment.requireActivity()).apply {
            val view =
                LayoutInflater.from(fragment.requireActivity())
                    .inflate(R.layout.dialog_other_error, null)
            setView(view)
            setPositiveButton(fragment.getString(R.string.dialog_error_positive_button)) { _, _ ->
                onPositiveButtonClickListener()
            }
        }.create().apply {
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setCancelable(false)
            show()
        }
    }
}