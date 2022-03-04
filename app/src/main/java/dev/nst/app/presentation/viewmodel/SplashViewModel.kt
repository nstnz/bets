package dev.nst.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.nst.bets.data.prefs.BetsPreferences
import dev.nst.navigation.NavigationFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow.DROP_OLDEST
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val prefs: BetsPreferences
) : ViewModel() {

    private val _navigateFlow = MutableSharedFlow<NavigationFlow>(1, 1, DROP_OLDEST)
    val navigateFlow: Flow<NavigationFlow> get() = _navigateFlow

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _navigateFlow.emit(NavigationFlow.BetsFlow(prefs.getLastScreen()))
        }
    }
}