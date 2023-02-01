package ru.rubik.dotastats.shared.presentation.errors

sealed interface DialogErrors {
    object NetworkConnectionError: DialogErrors
    object OtherError: DialogErrors
}