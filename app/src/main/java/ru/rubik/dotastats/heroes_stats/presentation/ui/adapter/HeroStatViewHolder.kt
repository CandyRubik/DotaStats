package ru.rubik.dotastats.heroes_stats.presentation.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import ru.rubik.dotastats.databinding.ItemHeroStatBinding
import ru.rubik.dotastats.heroes_stats.domain.entities.HeroStat

class HeroStatViewHolder(private val binding: ItemHeroStatBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(data: HeroStat) {
        with(binding) {
            name.text = data.name
            winPercent.setPositivePercent(data.turboWinPercent)
        }
    }
}