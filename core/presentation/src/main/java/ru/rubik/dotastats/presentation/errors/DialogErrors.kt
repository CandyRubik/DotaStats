package ru.rubik.dotastats.presentation.errors

sealed interface DialogErrors {
    object NetworkConnectionError: DialogErrors
    object OtherError: DialogErrors
}