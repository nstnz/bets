package dev.nst.bets.data.network.api

import dev.nst.bets.data.network.response.MatchesResponse
import dev.nst.bets.data.network.response.ResultsResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface MatchesApi {

    @GET("ca0a1135-0ffe-43a7-aed6-3df536c5a68d")
    suspend fun getMatches(): MatchesResponse

    @GET("0eac0494-be7a-4a8e-89ef-cc85d7987fd0")
    suspend fun getResults(): ResultsResponse
}