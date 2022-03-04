package dev.nst.bets.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import dev.nst.bets.data.db.model.MATCH_TABLE
import dev.nst.bets.data.db.model.MatchDbModel
import kotlinx.coroutines.flow.Flow

@Dao
interface MatchesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: MatchDbModel)

    @Update
    fun update(note: MatchDbModel)

    @Query("select * from $MATCH_TABLE")
    fun getAllMatches(): Flow<List<MatchDbModel>>

    @Query("delete from $MATCH_TABLE")
    fun deleteAllMatches()

    @Query("update $MATCH_TABLE set team1Bet=NULL, team2Bet=NULL")
    fun resetResults()
}