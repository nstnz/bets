package dev.nst.core.presentation.ui.activity

import androidx.appcompat.app.AppCompatActivity
import dev.nst.navigation.INavigatable
import dev.nst.navigation.Navigator

abstract class BaseActivity() : AppCompatActivity(), INavigatable {

    protected val navigator: Navigator = Navigator()
}