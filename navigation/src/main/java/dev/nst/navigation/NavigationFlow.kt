package dev.nst.navigation

sealed class NavigationFlow(val deepLinkRoot: String) {

    companion object {
        private const val APP = "app"
        private const val BETS = "bets"
    }

    object AppFlow : NavigationFlow(APP)

    object BetsFlow : NavigationFlow(BETS)
}