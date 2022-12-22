package ru.rubik.dotastats.heroes_stats.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import ru.rubik.dotastats.databinding.ItemHeroStatBinding
import ru.rubik.dotastats.heroes_stats.domain.entities.HeroStatsItem

class HeroStatViewHolder(private val binding: ItemHeroStatBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(data: HeroStatsItem) {
        with(binding) {
            name.text = data.name
            winPercent.setPositivePercent(data.turboWinPercent)
        }
    }
}