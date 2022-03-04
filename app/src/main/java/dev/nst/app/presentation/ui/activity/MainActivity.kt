package dev.nst.app.presentation.ui.activity

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import dev.nst.app.R
import dev.nst.app.R.layout
import dev.nst.bets.presentation.viewmodel.BETS_RESULTS_SCREEN_NAME
import dev.nst.core.presentation.ui.activity.BaseActivity
import dev.nst.navigation.NavigationFlow
import dev.nst.navigation.NavigationGraphDirections

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navigationHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        navigator.navController = navController
    }

    override fun navigateToFlow(flow: NavigationFlow) {
        return when (flow) {
            is NavigationFlow.BetsFlow -> {
                //todo choose start destination
                when (flow.startScreen) {
                    BETS_RESULTS_SCREEN_NAME -> navigator.navigate(NavigationGraphDirections.actionBetsFlow())
                    else                     -> navigator.navigate(NavigationGraphDirections.actionBetsFlow())
                }
            }
            else                       -> navigator.navigate(NavigationGraphDirections.actionAppFlow())
        }
    }
}