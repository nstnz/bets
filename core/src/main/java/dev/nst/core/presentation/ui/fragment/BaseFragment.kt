package dev.nst.core.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import dev.nst.navigation.INavigatable
import dev.nst.navigation.NavigationFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<V : ViewBinding>(
    private val inflate: Inflate<V>
) : Fragment() {

    protected lateinit var binding: V
        private set

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = inflate(inflater, container, false)
        return binding.root
    }

    protected fun launchInMain(block: suspend CoroutineScope.() -> Unit) {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main, block = block)
    }

    protected fun navigate(directions: NavDirections) {
        findNavController().navigate(directions)
    }

    protected fun navigate(flow: NavigationFlow) {
        (requireActivity() as? INavigatable)?.navigateToFlow(flow)
    }
}