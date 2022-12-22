package ru.rubik.dotastats.heroes_stats.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.rubik.dotastats.R
import ru.rubik.dotastats.databinding.ItemHeroStatBinding
import ru.rubik.dotastats.heroes_stats.domain.entities.HeroStat

class HeroesStatsAdapter(
    diffCallback: DiffUtil.ItemCallback<HeroStat>,
) : ListAdapter<HeroStat, HeroStatViewHolder>(diffCallback) {

    var itemsList: List<HeroStat> = listOf()
        set(value) {
            field = value
            submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroStatViewHolder {
        val binding =
            ItemHeroStatBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return HeroStatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HeroStatViewHolder, position: Int) {
        holder.itemView.animation =
            AnimationUtils.loadAnimation(holder.itemView.context, R.anim.item_animation)
        holder.bind(itemsList[position])
    }
}