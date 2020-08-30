package rs.sloman.oktomaca.repo

import rs.sloman.oktomaca.network.GithubApi
import javax.inject.Inject

/** Implementing the Repo pattern. Could have implemented a Repository interface
 * in order to allow multiple Repo implementations.*/
class Repo @Inject constructor(private val githubApi: GithubApi){

    suspend fun getProfile() = githubApi.getProfile()
    suspend fun getRepos(url: String) = githubApi.getRepos(url)
    suspend fun getCommits(url: String) = githubApi.getCommits(url)

}