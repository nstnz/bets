package dev.nst.bets.presentation.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import dev.nst.bets.databinding.ViewBetPickerBinding

class BetPickerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var binding = ViewBetPickerBinding.inflate(LayoutInflater.from(context), this)
    var currentBet = 0
        private set

    init {
        with(binding) {
            plusButton.setOnClickListener {
                currentBet++
                if (currentBet > 100) currentBet = 100
                betTextView.text = currentBet.toString()
            }
            minusButton.setOnClickListener {
                currentBet--
                if (currentBet < 0) currentBet = 0
                betTextView.text = currentBet.toString()
            }
        }
    }

    fun setTeamTitle(title: String) {
        binding.teamTitleTextView.text = title
    }

    fun setBet(bet: Int) {
        currentBet = bet
        binding.betTextView.text = currentBet.toString()
    }
}