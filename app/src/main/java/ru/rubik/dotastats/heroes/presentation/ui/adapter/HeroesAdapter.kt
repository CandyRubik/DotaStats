package ru.rubik.dotastats.heroes.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.rubik.dotastats.R
import ru.rubik.dotastats.databinding.ItemHeroStatBinding
import ru.rubik.dotastats.shared.heroes.domain.models.Hero

class HeroesAdapter(
    diffCallback: DiffUtil.ItemCallback<Hero>,
) : ListAdapter<Hero, HeroViewHolder>(diffCallback) {

    var itemsList: List<Hero> = listOf()
        set(value) {
            field = value
            submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        val binding =
            ItemHeroStatBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return HeroViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        holder.itemView.animation =
            AnimationUtils.loadAnimation(holder.itemView.context, R.anim.item_animation)
        holder.bind(getItem(position))
    }
}