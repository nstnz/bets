package dev.nst.bets.domain.model

data class MatchModel(
    val id: String,
    val team1: String,
    val team2: String,
    var team1Bet: Int? = null,
    var team2Bet: Int? = null,
    val team1Points: Int? = null,
    val team2Points: Int? = null,
)
