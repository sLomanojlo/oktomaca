package rs.sloman.oktomaca.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import rs.sloman.oktomaca.model.Profile
import rs.sloman.oktomaca.network.Status
import rs.sloman.oktomaca.repo.Repo


class MainViewModel @ViewModelInject constructor(val repo: Repo) : ViewModel() {

    // The internal MutableLiveDatas that stores the values
    private val _profile = MutableLiveData<Profile>()
    private val _status = MutableLiveData<Status>()

    // The external immutable LiveDatas for outside use
    val status: LiveData<Status>
    get() = _status

    val profile: LiveData<Profile>
    get() = _profile

    init {
        getProfile()
    }


    private fun getProfile() {
        viewModelScope.launch {
            _status.value = Status.LOADING

            try {
                _profile.value = repo.getProfile().body()
                _status.value = Status.DONE
            } catch (e: Exception) {
                _status.value = Status.ERROR
               // _profile.value = Profile()
            }
        }

    }


}