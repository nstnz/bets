package dev.nst.bets.domain.usecase

import dev.nst.bets.data.repository.MatchesRepository
import dev.nst.bets.domain.model.MatchModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMatchesUseCase @Inject constructor(
    private val repository: MatchesRepository,
) {

    val matchesFlow: Flow<List<MatchModel>> get() = repository.matchesFlow
}