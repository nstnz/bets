package dev.nst.app.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.nst.bets.data.db.dao.MatchesDao
import dev.nst.bets.data.db.model.MatchDbModel

const val BETS_DATABASE_NAME = "bets_database"

@Database(entities = [MatchDbModel::class], version = 1)
abstract class BetsDatabase : RoomDatabase() {

    abstract fun getMatchesDao(): MatchesDao
}