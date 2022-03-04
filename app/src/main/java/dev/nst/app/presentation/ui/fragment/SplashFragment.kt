package dev.nst.app.presentation.ui.fragment

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.migration.OptionalInject
import dev.nst.app.R
import dev.nst.app.databinding.FragmentSplashBinding
import dev.nst.app.presentation.viewmodel.SplashViewModel
import dev.nst.core.presentation.ui.fragment.BaseFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect

@OptionalInject
@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {

    companion object {
        const val TIME_DELAY_SPLASH = 3000L
    }

    private val vm: SplashViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rotation = AnimationUtils.loadAnimation(requireContext(), R.anim.progress_rotation)
        rotation.fillAfter = true
        binding.progressView.startAnimation(rotation)

        launchInMain {
            delay(TIME_DELAY_SPLASH) //delay to imitate loading process and to show beautiful splash screen :)
            vm.navigateFlow.collect { flow ->
                navigate(flow)
            }
        }
    }
}