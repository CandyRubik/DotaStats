package ru.rubik.dotastats.profile.data.mappers

import ru.rubik.dotastats.profile.data.entities.MatchInfoRawResponse
import ru.rubik.dotastats.profile.domain.models.MatchInfoRaw

class MatchesMapper {
    fun mapToMatchInfoRaw(source: MatchInfoRawResponse): MatchInfoRaw {
        return MatchInfoRaw(
            id = source.id,
            heroId = source.heroId,
            kills = source.kills,
            deaths = source.deaths,
            assists = source.assists,
            xpPerMinute = source.xpPerMinute,
        )
    }
}