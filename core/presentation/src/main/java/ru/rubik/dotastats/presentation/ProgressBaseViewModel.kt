package ru.rubik.dotastats.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ru.rubik.dotastats.presentation.errors.DialogErrors
import java.io.IOException

abstract class ProgressBaseViewModel : ViewModel() {

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _error: MutableStateFlow<DialogErrors?> = MutableStateFlow(null)
    val error = _error.asStateFlow()

    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        if(throwable is IOException) {
            _error.value = DialogErrors.NetworkConnectionError
        } else {
            _error.value = DialogErrors.OtherError
        }
    }

    fun enableLoading() {
        _isLoading.value = true
    }

    fun disableLoading() {
        _isLoading.value = false
    }

    fun disableError() {
        _error.value = null
    }
}
