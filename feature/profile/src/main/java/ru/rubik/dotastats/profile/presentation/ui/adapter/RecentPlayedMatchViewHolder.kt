package ru.rubik.dotastats.profile.presentation.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import ru.rubik.dotastats.design.R
import ru.rubik.dotastats.profile.databinding.ItemRecentPlayedMatchBinding
import ru.rubik.dotastats.profile.domain.models.MatchInfo

class RecentPlayedMatchViewHolder(
    private val binding: ItemRecentPlayedMatchBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(data: MatchInfo) {
        with(binding) {
            image.load(data.heroUrl) {
                crossfade(true)
                placeholder(R.drawable.loading_animation)
                transformations(RoundedCornersTransformation(8f))
            }
            kills.text = data.kills.toString()
            deaths.text = data.deaths.toString()
            assists.text = data.assists.toString()
            xpm.text = data.xpPerMinute.toString()
        }
    }
}