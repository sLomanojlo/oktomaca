package rs.sloman.oktomaca.network

import retrofit2.Response
import retrofit2.http.GET
import rs.sloman.oktomaca.model.Profile


interface GithubApi {

    @GET("/users/octocat")
    suspend fun getProfile(): Response<Profile>

}