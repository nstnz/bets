package dev.nst.bets.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.nst.bets.domain.model.MatchModel
import dev.nst.bets.domain.usecase.GetMatchesUseCase
import dev.nst.bets.domain.usecase.SaveBetUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow.DROP_OLDEST
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BetsListViewModel @Inject constructor(
    getMatchesUseCase: GetMatchesUseCase,
    private val saveBetUseCase: SaveBetUseCase,
) : ViewModel() {

    private var canGoToResults = false
    val matchesFlow: Flow<List<MatchModel>> = getMatchesUseCase.matchesFlow.onEach {
        canGoToResults = it.any { it.team1Bet != null && it.team2Bet != null }
    }

    private val _toResultsFlow = MutableSharedFlow<Boolean>(0, 1, DROP_OLDEST)
    val toResultsFlow: Flow<Boolean> get() = _toResultsFlow

    fun navigateToResults() {
        viewModelScope.launch(Dispatchers.IO) {
            _toResultsFlow.emit(canGoToResults)
        }
    }

    fun onSetBet(matchModel: MatchModel, team1Score: Int, team2Score: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            saveBetUseCase.retrieveData(matchModel.apply {
                this.team1Bet = team1Score
                this.team2Bet = team2Score
            }).collect()
        }
    }
}