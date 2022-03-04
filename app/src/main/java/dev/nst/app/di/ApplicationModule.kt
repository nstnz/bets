package dev.nst.app.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.nst.app.data.db.BETS_DATABASE_NAME
import dev.nst.app.data.db.BetsDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Singleton
    @Provides
    fun provideYourDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        BetsDatabase::class.java,
        BETS_DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideMatchesDao(db: BetsDatabase) = db.getMatchesDao()
}