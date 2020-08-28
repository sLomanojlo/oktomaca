package rs.sloman.oktomaca.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import rs.sloman.oktomaca.model.Profile
import rs.sloman.oktomaca.model.UserRepo
import rs.sloman.oktomaca.network.Status
import rs.sloman.oktomaca.repo.Repo
import rs.sloman.oktomaca.util.Constants
import rs.sloman.oktomaca.util.Constants.CONNECTION_ERROR


class ProfileViewModel @ViewModelInject constructor(val repo: Repo) : ViewModel() {

    // The internal MutableLiveDatas storing the values
    private val _profile = MutableLiveData<Profile>()
    private val _status = MutableLiveData<Status>()
    private val _userReposList = MutableLiveData<List<UserRepo>>()


    // The external immutable LiveDatas for outside use
    val status: LiveData<Status>
        get() = _status

    val profile: LiveData<Profile>
        get() = _profile

    val userReposList: LiveData<List<UserRepo>>
        get() = _userReposList

    init {
        getProfile()
    }


    fun getProfile() {

        viewModelScope.launch {
            _status.value = Status.LOADING

            try {
                //TODO Handle exceptions
                val responseProfile = repo.getProfile()
                if (responseProfile.isSuccessful) {
                    _profile.value = responseProfile.body()
                    val responseRepos = repo.getRepos(profile.value!!.reposUrl)

                    if (responseRepos.isSuccessful) {
                        _userReposList.value = responseRepos.body()
                        _status.value = Status.DONE

                    } else throw Exception(CONNECTION_ERROR)
                } else throw Exception(CONNECTION_ERROR)

            } catch (e: Exception) {
                e.printStackTrace()
                _status.value = Status.ERROR
            }
        }

    }


}