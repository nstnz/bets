package dev.nst.bets.domain.mapper

import dev.nst.bets.data.db.model.MatchDbModel
import dev.nst.bets.data.network.response.MatchesResponseItem
import dev.nst.bets.domain.model.MatchModel
import dev.nst.core.domain.mapper.BaseDataMapper
import javax.inject.Inject

class MatchesMapper @Inject constructor(
) : BaseDataMapper<MatchModel, MatchDbModel, MatchesResponseItem>() {

    override fun mapToDomain(model: MatchDbModel): MatchModel {
        return MatchModel(
            id = model.id,
            team1 = model.team1,
            team2 = model.team2,
            team1Bet = model.team1Bet,
            team2Bet = model.team2Bet
        )
    }

    override fun mapToDb(model: MatchesResponseItem): MatchDbModel {
        return MatchDbModel(
            team1 = model.team1.orEmpty(),
            team2 = model.team2.orEmpty(),
            id = model.team1.orEmpty() + model.team2.orEmpty()
        )
    }

    override fun mapToDb2(model: MatchModel): MatchDbModel {
        return MatchDbModel(
            id = model.id,
            team1 = model.team1,
            team2 = model.team2,
            team1Bet = model.team1Bet,
            team2Bet = model.team2Bet
        )
    }
}