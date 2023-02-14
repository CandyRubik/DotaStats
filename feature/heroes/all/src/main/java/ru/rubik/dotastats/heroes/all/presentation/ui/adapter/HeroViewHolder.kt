package ru.rubik.dotastats.heroes.all.presentation.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import ru.rubik.dotastats.design.R
import ru.rubik.dotastats.heroes.all.databinding.ItemHeroBinding
import ru.rubik.dotastats.heroes_api.domain.models.Hero

class HeroViewHolder(private val binding: ItemHeroBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(data: Hero, onItemClickListener: (Hero) -> Unit) {
        with(binding) {
            root.setOnClickListener {
                onItemClickListener(data)
            }
            name.text = data.name
            image.load(data.imageUrl) {
                crossfade(true)
                placeholder(R.drawable.loading_animation)
                transformations(RoundedCornersTransformation(8f))
            }
        }
    }
}