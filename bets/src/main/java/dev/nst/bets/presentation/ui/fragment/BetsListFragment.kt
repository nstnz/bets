package dev.nst.bets.presentation.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.migration.OptionalInject
import dev.nst.bets.R
import dev.nst.bets.databinding.FragmentBetsListBinding
import dev.nst.bets.domain.model.MatchModel
import dev.nst.bets.presentation.ui.adapter.MatchesAdapter
import dev.nst.bets.presentation.ui.dialog.NewBetDialog
import dev.nst.bets.presentation.viewmodel.BetsListViewModel
import dev.nst.core.presentation.ui.fragment.BaseFragment
import kotlinx.coroutines.flow.collect

@OptionalInject
@AndroidEntryPoint
class BetsListFragment : BaseFragment<FragmentBetsListBinding>(FragmentBetsListBinding::inflate) {

    private val viewModel: BetsListViewModel by viewModels()

    private val adapter by lazy { MatchesAdapter(this::clickOnMatch) }
    private var newBetDialog: NewBetDialog? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setLoadingState(visible = true)

        with(binding) {
            matchesRecyclerView.adapter = adapter
            getResultsButton.setOnClickListener {
                viewModel.navigateToResults()
            }
        }

        launchInMain {
            viewModel.matchesFlow.collect {
                setLoadingState(visible = false)
                adapter.items = it
            }
        }

        launchInMain {
            viewModel.toResultsFlow.collect {
                if (it) {
                    navigate(BetsListFragmentDirections.actionBetsListFragmentToBetsResultsFragment())
                } else {
                    Toast.makeText(requireContext(), R.string.bets_choose_bet, Toast.LENGTH_SHORT).show()
                }
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

    private fun clickOnMatch(match: MatchModel) {
        newBetDialog?.dismiss()
        newBetDialog = NewBetDialog(requireContext(), match, viewModel::onSetBet)
        newBetDialog?.show()
    }

    override fun onDestroy() {
        newBetDialog?.dismiss()
        newBetDialog = null
        super.onDestroy()
    }
}