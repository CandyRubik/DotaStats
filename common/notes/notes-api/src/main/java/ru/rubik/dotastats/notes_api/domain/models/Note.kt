package ru.rubik.dotastats.notes_api.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Note(
    val id: Long,
    val profileId: String,
    val title: String,
    val description: String,
) : Parcelable