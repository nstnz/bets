package dev.nst.bets.presentation.ui.dialog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import dev.nst.bets.databinding.DialogNewBetBinding
import dev.nst.bets.domain.model.MatchModel
import dev.nst.core.presentation.ui.dialog.BaseDialog

class NewBetDialog(
    context: Context,
    private val match: MatchModel,
    private val onSetBet: (MatchModel, Int, Int) -> Unit
) : BaseDialog(context) {

    private lateinit var viewBinding: DialogNewBetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = DialogNewBetBinding.inflate(LayoutInflater.from(context))
        setContentView(viewBinding.root)

        with(viewBinding) {
            team1BetView.setTeamTitle(match.team1)
            team2BetView.setTeamTitle(match.team2)
            team1BetView.setBet(match.team1Bet ?: 0)
            team2BetView.setBet(match.team2Bet ?: 0)

            okButton.setOnClickListener {
                onSetBet(match, team1BetView.currentBet, team2BetView.currentBet)
                dismiss()
            }

            cancelButton.setOnClickListener {
                dismiss()
            }
        }
    }
}