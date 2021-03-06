package rs.sloman.oktomaca.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import rs.sloman.oktomaca.network.GithubApi
import rs.sloman.oktomaca.util.Constants
import javax.inject.Singleton

/** Where the magic begins! Dagger Hilt provides singleton instances for our MVVM architecture*/

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    /**Function that returns a singleton GithubApi*/
    @Singleton
    @Provides
    fun provideGithubApi(): GithubApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .baseUrl(Constants.BASE_URL)
            .build()
            .create(GithubApi::class.java)
    }


    /**Function that returns a singleton Gson used for data string manipulation*/
    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson = GsonBuilder().setDateFormat("yyyy-MM-ddTHH:mm:ssZ").create()

}