package dev.nst.bets.presentation.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.migration.OptionalInject
import dev.nst.bets.R
import dev.nst.bets.databinding.FragmentBetsResultsBinding
import dev.nst.bets.presentation.ui.adapter.MatchesAdapter
import dev.nst.bets.presentation.viewmodel.BetsResultsViewModel
import dev.nst.core.presentation.ui.fragment.BaseFragment
import kotlinx.coroutines.flow.collect

@OptionalInject
@AndroidEntryPoint
class BetsResultsFragment : BaseFragment<FragmentBetsResultsBinding>(FragmentBetsResultsBinding::inflate) {

    private val viewModel: BetsResultsViewModel by viewModels()

    private val adapter by lazy { MatchesAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setLoadingState(visible = true)

        with(binding) {
            resultsRecyclerView.adapter = adapter
            resetResultsButton.setOnClickListener {
                viewModel.resetResults()
                navigate(BetsResultsFragmentDirections.actionBetsResultsFragmentToBetsListFragment())
            }
        }

        launchInMain {
            viewModel.resultsFlow.collect {
                setLoadingState(visible = false)
                adapter.items = it
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.updateLastOpenedScreen()
    }

    private fun setLoadingState(visible: Boolean) {
        binding.progressView.visibility = if (visible) View.VISIBLE else View.GONE
    }
}