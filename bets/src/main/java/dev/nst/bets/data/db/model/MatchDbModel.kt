package dev.nst.bets.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

const val MATCH_TABLE = "t_matches"

@Entity(tableName = MATCH_TABLE)
data class MatchDbModel(
    @PrimaryKey(autoGenerate = false) val id: String,
    val team1: String,
    val team2: String,
    val team1Bet: Int? = null,
    val team2Bet: Int? = null,
)