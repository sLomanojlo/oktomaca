package rs.sloman.oktomaca.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url
import rs.sloman.oktomaca.model.CommitBase
import rs.sloman.oktomaca.model.Profile
import rs.sloman.oktomaca.model.UserRepo


/** Classical Retrofit interface DAO with suspend functions.*/
interface GithubApi {

    @GET("/users/octocat")
    suspend fun getProfile(): Response<Profile>

    @GET
    suspend fun getRepos(@Url url: String): Response<List<UserRepo>>

    @GET("/repos/octocat/{repoName}/commits")
    suspend fun getCommits(
        @Path(value = "repoName", encoded = true) repoName: String
    ): Response<List<CommitBase>>

}