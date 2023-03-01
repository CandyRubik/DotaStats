package ru.rubik.dotastats.heroes.all.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.rubik.dotastats.heroes.all.databinding.ItemHeroBinding
import ru.rubik.dotastats.heroes_api.domain.models.Hero

class HeroesAdapter(
    private val onItemClickListener: (Hero) -> Unit,
    diffCallback: DiffUtil.ItemCallback<Hero>,
) : ListAdapter<Hero, HeroViewHolder>(diffCallback) {

    var itemsList: List<Hero> = listOf()
        set(value) {
            field = value
            submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        val binding =
            ItemHeroBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return HeroViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClickListener)
    }
}