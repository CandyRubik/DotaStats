package ru.rubik.dotastats.heroes.all.presentation.ui.adapter

import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import ru.rubik.dotastats.heroes.all.R

class HeroItemAnimator : DefaultItemAnimator() {

    override fun animateAdd(holder: RecyclerView.ViewHolder?): Boolean {
        holder?.itemView?.animation =
            AnimationUtils.loadAnimation(holder?.itemView?.context, R.anim.item_animation)
        return super.animateAdd(holder)
    }
}