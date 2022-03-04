package dev.nst.bets.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.nst.bets.domain.usecase.ResultsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BetsResultsViewModel @Inject constructor(
    private val resultsUseCase: ResultsUseCase,
) : ViewModel() {

    fun resetResults() {
        viewModelScope.launch(Dispatchers.IO) {
            resultsUseCase.resetResults().collect()
        }
    }
}