package dev.nst.bets.data.network.api

import dev.nst.bets.data.network.response.MatchesResponse
import dev.nst.bets.data.network.response.ResultsResponse
import retrofit2.http.GET

interface MatchesApi {

    @GET("ca0a1135-0ffe-43a7-aed6-3df536c5a68d")
    suspend fun getMatches(): MatchesResponse

    @GET("ca0a1135-0ffe-43a7-aed6-3df536c5a68d")
    suspend fun getResults(): ResultsResponse
}