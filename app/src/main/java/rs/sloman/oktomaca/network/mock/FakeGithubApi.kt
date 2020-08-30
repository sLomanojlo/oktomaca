package rs.sloman.oktomaca.network.mock

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url
import rs.sloman.oktomaca.model.Profile
import rs.sloman.oktomaca.model.UserRepo


interface FakeGithubApi {

    @GET("user")
    suspend fun getProfile(): Response<Profile>

    @GET()
    suspend fun getRepos(@Url url: String): Response<List<UserRepo>>
}