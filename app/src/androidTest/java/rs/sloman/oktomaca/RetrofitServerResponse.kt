package rs.sloman.oktomaca

import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.*
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Response
import rs.sloman.oktomaca.model.Profile
import rs.sloman.oktomaca.model.UserRepo
import rs.sloman.oktomaca.network.mock.FakeGithubApi
import rs.sloman.oktomaca.network.mock.RetrofitMock
import timber.log.Timber

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class RetrofitServerResponse {

    @Test
    fun testGetProfile() {

        val fakeService: FakeGithubApi = RetrofitMock.getFakeService()
        lateinit var response : Response<Profile>

        CoroutineScope(Dispatchers.Main).launch{
            response = fakeService.getProfile()

            Timber.d("${response.body()}")
            assertEquals(response.body()?.id, 1)
            assertEquals(response.body()?.name, "Slo")
            assertEquals(response.body()?.company, "SRA")
            assertEquals(response.body()?.reposUrl, "http")
            assertEquals(response.body()?.imgUrl, "url")

        }

        Timber.d("end")

    }

    @Test
    fun testGetRepos() {

        val fakeService: FakeGithubApi = RetrofitMock.getFakeService()
        lateinit var response : Response<List<UserRepo>>

        CoroutineScope(Dispatchers.Main).launch{
            response = fakeService.getRepos("test")

            Timber.d("${response.body()}")

            assert(response.body()?.size == 2)

        }

        Timber.d("end")

    }
}