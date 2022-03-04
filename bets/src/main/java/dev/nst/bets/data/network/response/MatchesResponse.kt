package dev.nst.bets.data.network.response

import com.google.gson.annotations.SerializedName

data class MatchesResponse(
    @SerializedName("matches") val matches: List<MatchesResponseItem>,
)

data class MatchesResponseItem(
    @SerializedName("team1") val team1: String?,
    @SerializedName("team2") val team2: String?,
)
