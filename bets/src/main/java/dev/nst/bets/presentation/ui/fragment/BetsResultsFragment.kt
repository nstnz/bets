package dev.nst.bets.presentation.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.migration.OptionalInject
import dev.nst.bets.databinding.FragmentBetsResultsBinding
import dev.nst.bets.presentation.viewmodel.BetsResultsViewModel
import dev.nst.core.presentation.ui.fragment.BaseFragment

@OptionalInject
@AndroidEntryPoint
class BetsResultsFragment : BaseFragment<FragmentBetsResultsBinding>(FragmentBetsResultsBinding::inflate) {

    private val viewModel: BetsResultsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            resetResultsButton.setOnClickListener {
                viewModel.resetResults()
                findNavController().popBackStack()
            }
        }
    }
}