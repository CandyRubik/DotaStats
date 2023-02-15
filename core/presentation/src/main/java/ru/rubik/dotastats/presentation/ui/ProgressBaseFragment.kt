package ru.rubik.dotastats.presentation.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import ru.rubik.dotastats.presentation.dialogs.DialogErrors
import ru.rubik.dotastats.presentation.dialogs.DialogFactory
import ru.rubik.dotastats.presentation.vm.ProgressBaseViewModel

abstract class ProgressBaseFragment(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId) {

    protected abstract val viewModel: ProgressBaseViewModel

    private var loadingDialog: AlertDialog? = null

    private var errorDialog: AlertDialog? = null

    private var dialogFactory: DialogFactory? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialogFactory = DialogFactory(this)

        bindDialogs()
    }

    private fun bindDialogs() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.isLoading.collect { isLoading ->
                if (loadingDialog != null && isLoading) return@collect

                loadingDialog = if (isLoading && loadingDialog == null) {
                    errorDialog?.dismiss()
                    dialogFactory?.createLoaderFragment()
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
                        dialogFactory?.createNetworkErrorDialog {
                            viewModel.disableError()
                        }
                    }
                    DialogErrors.OtherError -> {
                        loadingDialog?.dismiss()
                        dialogFactory?.createOtherErrorDialog {
                            viewModel.disableError()
                        }
                    }
                    null -> {
                        errorDialog?.dismiss()
                        null
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        loadingDialog?.dismiss()
        loadingDialog = null
        dialogFactory = null
        super.onDestroyView()
    }
}