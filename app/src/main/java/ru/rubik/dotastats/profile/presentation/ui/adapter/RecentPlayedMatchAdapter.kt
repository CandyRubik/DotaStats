package ru.rubik.dotastats.profile.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.rubik.dotastats.databinding.ItemRecentPlayedMatchBinding
import ru.rubik.dotastats.profile.domain.models.MatchInfo

class RecentPlayedMatchAdapter(
    diffCallback: DiffUtil.ItemCallback<MatchInfo>,
) : ListAdapter<MatchInfo, RecentPlayedMatchViewHolder>(diffCallback) {

    var itemsList: List<MatchInfo> = listOf()
        set(value) {
            field = value
            submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentPlayedMatchViewHolder {
        val binding =
            ItemRecentPlayedMatchBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return RecentPlayedMatchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecentPlayedMatchViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}