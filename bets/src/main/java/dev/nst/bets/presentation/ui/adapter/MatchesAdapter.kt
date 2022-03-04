package dev.nst.bets.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import dev.nst.bets.R
import dev.nst.bets.databinding.ItemMatchBinding
import dev.nst.bets.domain.model.MatchModel
import dev.nst.bets.presentation.ui.adapter.MatchesAdapter.MatchesViewHolder
import kotlinx.android.extensions.LayoutContainer

class MatchesAdapter(
    private val clickOnMatch: (MatchModel) -> Unit
) : RecyclerView.Adapter<MatchesViewHolder>() {

    var items = listOf<MatchModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchesViewHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.item_match, parent, false)
        return MatchesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MatchesViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class MatchesViewHolder(
        override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        private val binding = ItemMatchBinding.bind(this.itemView)

        fun bind(item: MatchModel) {
            with(binding) {
                sideOneTitle.text = item.team1
                sideTwoTitle.text = item.team2
                itemView.setOnClickListener { clickOnMatch(item) }

                sideOneBet.text = item.team1Bet?.toString().orEmpty()
                sideTwoBet.text = item.team2Bet?.toString().orEmpty()

                if (item.team1Points != null && item.team2Points != null) {
                    realScore.visibility = View.VISIBLE
                    realScore.setTextColor(
                        ContextCompat.getColor(
                            realScore.context,
                            if (item.team1Bet == item.team1Points && item.team2Bet == item.team2Points) R.color.accent_color else R.color.secondary_text_color
                        )
                    )
                } else {
                    realScore.visibility = View.GONE
                }
            }
        }
    }
}