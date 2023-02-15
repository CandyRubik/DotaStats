package ru.rubik.dotastats.presentation.ext

import android.content.Context

fun Context.toDp(value: Float): Float {
    return resources.displayMetrics.density * value
}

fun Context.getWith(): Float {
    return resources.displayMetrics.widthPixels.toFloat()
}