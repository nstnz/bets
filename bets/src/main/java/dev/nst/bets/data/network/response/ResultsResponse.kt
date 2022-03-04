package dev.nst.bets.data.network.response

import com.google.gson.annotations.SerializedName

data class ResultsResponse(
    @SerializedName("matches") val matches: List<ResultsResponseItem>,
)

data class ResultsResponseItem(
    @SerializedName("team1") val team1: String?,
    @SerializedName("team2") val team2: String?,
    @SerializedName("team1_points") val team1Score: Int?,
    @SerializedName("team2_points") val team2Score: Int?,
) {

    val id: String
        get() = team1.orEmpty() + team2.orEmpty()
}
