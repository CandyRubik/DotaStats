package ru.rubik.dotastats.heroes_stats.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.rubik.dotastats.databinding.FragmentHeroesStatsBinding
import ru.rubik.dotastats.databinding.ItemHeroStatBinding
import ru.rubik.dotastats.heroes_stats.domain.entities.HeroStatsItem
import kotlin.properties.Delegates

class HeroesStatsAdapter(
    diffCallback: DiffUtil.ItemCallback<HeroStatsItem>,
) : ListAdapter<HeroStatsItem, HeroStatViewHolder>(diffCallback) {

    var itemsList: List<HeroStatsItem> by Delegates.observable(
        initialValue = listOf(),
        onChange = { _, _, newValue ->
            submitList(newValue)
        }
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroStatViewHolder {
        val binding =
            ItemHeroStatBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return HeroStatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HeroStatViewHolder, position: Int) {
        holder.bind(itemsList[position])
    }
}