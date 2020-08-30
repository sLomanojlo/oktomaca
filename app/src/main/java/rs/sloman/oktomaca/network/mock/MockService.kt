package rs.sloman.oktomaca.network.mock

import retrofit2.Response
import retrofit2.mock.BehaviorDelegate
import rs.sloman.oktomaca.model.Profile
import rs.sloman.oktomaca.model.UserRepo


class MockService(private val delegate: BehaviorDelegate<FakeGithubApi>) : FakeGithubApi {

    override suspend fun getProfile(): Response<Profile> {

        val response: Response<Profile> = Response.success(Profile(1, "Slo", "SRA", "http", "url"))
        return delegate.returningResponse(response).getProfile()
    }

    override suspend fun getRepos(url: String): Response<List<UserRepo>> {
        val response: Response<List<UserRepo>> = Response.success(
            listOf(
                UserRepo(0, "test", "test", 0, 0, "test", "test"),
                UserRepo(1, "test", "test", 0, 0, "test", "test")
            )
        )


        return delegate.returningResponse(response).getRepos("test")
    }

}