package dev.nst.navigation

import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions

class Navigator {

    var navController: NavController? = null

    fun navigate(directions: NavDirections) {
        navController?.navigate(directions)
    }
}