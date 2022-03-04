package dev.nst.bets.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.nst.bets.data.prefs.BetsPreferences
import dev.nst.bets.domain.model.MatchModel
import dev.nst.bets.domain.usecase.ResultsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow.DROP_OLDEST
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

const val BETS_RESULTS_SCREEN_NAME = "BetsResultsScreen"

@HiltViewModel
class BetsResultsViewModel @Inject constructor(
    private val resultsUseCase: ResultsUseCase,
    private val prefs: BetsPreferences,
) : ViewModel() {

    private val _resultsFlow = MutableSharedFlow<List<MatchModel>>(0, 1, DROP_OLDEST)
    val resultsFlow: Flow<List<MatchModel>> get() = _resultsFlow

    fun getResults() {
        viewModelScope.launch(Dispatchers.IO) {
            resultsUseCase.getResults().collect {
                _resultsFlow.emit(it)
            }
        }
    }

    fun resetResults() {
        viewModelScope.launch(Dispatchers.IO) {
            resultsUseCase.resetResults().collect()
        }
    }

    fun updateLastOpenedScreen() {
        prefs.setLastScreen(BETS_RESULTS_SCREEN_NAME)
    }
}