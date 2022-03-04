package dev.nst.bets.domain.usecase

import dev.nst.bets.data.repository.MatchesRepository
import dev.nst.bets.domain.model.MatchModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SaveBetUseCase @Inject constructor(
    private val repository: MatchesRepository
) {

    suspend fun retrieveData(param: MatchModel) = flow {
        emit(repository.saveBet(param))
    }
}