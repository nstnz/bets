package dev.nst.bets.domain.usecase

import dev.nst.bets.data.prefs.BetsPreferences
import dev.nst.bets.data.repository.MatchesRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ResultsUseCase @Inject constructor(
    private val repository: MatchesRepository,
    private val prefs: BetsPreferences,
) {

    suspend fun resetResults() = flow {
        emit(repository.resetResults())
    }
}