package ru.rubik.dotastats.heroes.presentation.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import ru.rubik.dotastats.databinding.ItemHeroStatBinding
import ru.rubik.dotastats.heroes.domain.entities.Hero

class HeroViewHolder(private val binding: ItemHeroStatBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(data: Hero) {
        with(binding) {
            name.text = data.name
            winPercent.setPositivePercent(data.turboWinPercent)
        }
    }
}