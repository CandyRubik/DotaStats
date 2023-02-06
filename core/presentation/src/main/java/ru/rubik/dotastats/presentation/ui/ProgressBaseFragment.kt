package ru.rubik.dotastats.presentation.ui

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import ru.rubik.dotastats.presentation.ProgressBaseViewModel
import ru.rubik.dotastats.presentation.R
import ru.rubik.dotastats.presentation.errors.DialogErrors

abstract class ProgressBaseFragment(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId) {

    protected abstract val viewModel: ProgressBaseViewModel

    private var loadingDialog: AlertDialog? = null

    private var errorDialog: AlertDialog? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindDialogs()
    }

    private fun bindDialogs() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.isLoading.collect { isLoading ->
                if (loadingDialog != null && isLoading) return@collect

                loadingDialog = if (isLoading && loadingDialog == null) {
                    errorDialog?.dismiss()
                    createLoaderFragment()
                } else {
                    loadingDialog?.dismiss()
                    null
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.error.collect { error ->

                if (errorDialog != null && error != null) return@collect

                errorDialog = when (error) {
                    DialogErrors.NetworkConnectionError -> {
                        loadingDialog?.dismiss()
                        createNetworkErrorDialog()
                    }
                    DialogErrors.OtherError -> {
                        loadingDialog?.dismiss()
                        createOtherErrorDialog()
                    }
                    null -> {
                        errorDialog?.dismiss()
                        null
                    }
                }
            }
        }
    }

    protected open fun createOtherErrorDialog(): AlertDialog? {
        return AlertDialog.Builder(requireActivity()).apply {
            val view =
                LayoutInflater.from(requireActivity()).inflate(R.layout.dialog_other_error, null)
            setView(view)
            setPositiveButton(getString(R.string.dialog_error_positive_button)) { _, _ ->
                viewModel.disableError()
            }
        }.create().apply {
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setCancelable(false)
            show()
        }
    }

    protected open fun createNetworkErrorDialog(): AlertDialog? {
        return AlertDialog.Builder(requireActivity()).apply {
            val view =
                LayoutInflater.from(requireActivity()).inflate(R.layout.dialog_network_error, null)
            setView(view)
            setPositiveButton(getString(R.string.dialog_error_positive_button)) { _, _ ->
                viewModel.disableError()
            }
        }.create().apply {
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setCancelable(false)
            show()
        }
    }

    protected open fun createLoaderFragment(): AlertDialog? {
        return AlertDialog.Builder(requireActivity()).apply {
            val view = LayoutInflater.from(requireActivity()).inflate(R.layout.dialog_loading, null)
            setView(view)
        }.create().apply {
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setCancelable(false)
            show()
        }
    }

    override fun onDestroyView() {
        loadingDialog?.dismiss()
        loadingDialog = null
        super.onDestroyView()
    }
}