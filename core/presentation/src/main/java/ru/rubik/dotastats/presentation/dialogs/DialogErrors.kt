package ru.rubik.dotastats.presentation.dialogs

sealed interface DialogErrors {
    object NetworkConnectionError: DialogErrors
    object OtherError: DialogErrors
}