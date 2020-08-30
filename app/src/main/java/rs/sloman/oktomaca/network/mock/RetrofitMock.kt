package rs.sloman.oktomaca.network.mock

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.mock.MockRetrofit
import retrofit2.mock.NetworkBehavior
import rs.sloman.oktomaca.util.Constants


/** FakeGithubApi that returns a MockService
 * populated with a NetworkBehavior attached to MockRetrofit*/
class RetrofitMock {

    companion object {
        fun getFakeService(): FakeGithubApi {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .baseUrl(Constants.BASE_URL)
                .build()

            val behavior = NetworkBehavior.create()
            val mock = MockRetrofit.Builder(retrofit)
                .networkBehavior(behavior)
                .build()
            val delegate = mock.create(FakeGithubApi::class.java)

            return MockService(delegate)

        }

    }
}