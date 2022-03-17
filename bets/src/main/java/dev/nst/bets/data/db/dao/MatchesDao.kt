package dev.nst.bets.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import dev.nst.bets.data.db.model.MATCH_TABLE
import dev.nst.bets.data.db.model.MatchDbModel
import kotlinx.coroutines.flow.Flow

@Dao
interface MatchesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(note: MatchDbModel)

    @Query("update $MATCH_TABLE set team1=:team1, team2=:team2 where id=:id")
    fun updateTeam(
        id: String,
        team1: String,
        team2: String,
    )

    @Query("SELECT id FROM $MATCH_TABLE WHERE id = :id LIMIT 1")
    fun getItemId(id: String): String?

    @Update
    fun update(note: MatchDbModel)

    @Query("select * from $MATCH_TABLE")
    fun getAllMatches(): Flow<List<MatchDbModel>>

    @Query("delete from $MATCH_TABLE")
    fun deleteAllMatches()

    @Query("update $MATCH_TABLE set team1Bet=NULL, team2Bet=NULL")
    fun resetResults()
}