package ru.rubik.dotastats.notes.all.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Note(
    val id: Long,
    val steamId: String,
    val title: String,
    val description: String,
) : Parcelable