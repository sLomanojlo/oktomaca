package rs.sloman.oktomaca.repo

import rs.sloman.oktomaca.network.GithubApi
import javax.inject.Inject


class Repo @Inject constructor(private val githubApi: GithubApi){

    suspend fun getProfile() = githubApi.getProfile()

}