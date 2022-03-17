package dev.nst.bets.data.repository

import dev.nst.bets.data.db.dao.MatchesDao
import dev.nst.bets.data.network.api.MatchesApi
import dev.nst.bets.data.prefs.BetsPreferences
import dev.nst.bets.domain.mapper.MatchesMapper
import dev.nst.bets.domain.model.MatchModel
import dev.nst.core.data.network.RetrofitProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.zip
import javax.inject.Inject

private const val MINUTE = 1000 * 60

class MatchesRepository @Inject constructor(
    private val retrofitProvider: RetrofitProvider,
    private val matchesDao: MatchesDao,
    private val prefs: BetsPreferences,
    private val matchesMapper: MatchesMapper
) {

    private val api by lazy { retrofitProvider.createApi(MatchesApi::class.java) }

    val matchesFlow: Flow<List<MatchModel>> = matchesDao.getAllMatches().mapLatest { matchesMapper.mapToDomain(it) }

    suspend fun loadMatches() {
        loadAndSaveMatches()
    }

    fun saveBet(model: MatchModel) {
        matchesDao.update(matchesMapper.mapToDb2(model))
    }

    fun resetResults() {
        matchesDao.resetResults()
    }

    suspend fun getResults(): Flow<List<MatchModel>> {
        return matchesDao.getAllMatches()
            .zip(flow { emit(api.getResults()) }) { dbModel, resultsList ->
                return@zip matchesMapper.mapToDomain(dbModel).onEach { domMatch ->
                    resultsList.matches.firstOrNull { it.id == domMatch.id }?.let {
                        domMatch.team1Points = it.team1Score
                        domMatch.team2Points = it.team2Score
                    }
                }
            }
    }

    private suspend fun loadAndSaveMatches() {
        if (System.currentTimeMillis() - prefs.getLastUpdatedTimestamp() >= MINUTE) {
            api.getMatches().matches.map {
                val dbModel = matchesMapper.mapToDb(it)
                if (matchesDao.getItemId(dbModel.id) != null) {
                    matchesDao.updateTeam(dbModel.id, dbModel.team1, dbModel.team2)
                } else {
                    matchesDao.insert(dbModel)
                }
            }
            prefs.setLastUpdatedTimestamp(System.currentTimeMillis())
        }
    }
}