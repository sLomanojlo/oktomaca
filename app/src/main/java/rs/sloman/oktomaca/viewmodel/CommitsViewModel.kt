package rs.sloman.oktomaca.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import rs.sloman.oktomaca.model.CommitBase
import rs.sloman.oktomaca.network.Status
import rs.sloman.oktomaca.repo.Repo
import rs.sloman.oktomaca.util.Constants


class CommitsViewModel @ViewModelInject constructor(val repo: Repo) : ViewModel() {


    // The internal MutableLiveData storing the values
    private val _commitsBase = MutableLiveData<List<CommitBase>>()
    private val _status = MutableLiveData<Status>()

    // The external immutable LiveData for outside use
    val commitsBase: LiveData<List<CommitBase>>
        get() = _commitsBase

    // The external immutable LiveDatas for outside use
    val status: LiveData<Status>
        get() = _status

    val repoName: MutableLiveData<String> = MutableLiveData()


     fun getCommitsBase(repoName: String?) {

        viewModelScope.launch {
            _status.value = Status.LOADING

            try {
                val commitResponse = repo.getCommits(repoName!!)

                if (commitResponse.isSuccessful) {
                    _commitsBase.value = commitResponse.body()
                    _status.value = Status.DONE
                } else throw Exception (Constants.CONNECTION_ERROR)

            } catch (e: Exception) {
                e.printStackTrace()
                _status.value = Status.ERROR
            }
        }

    }

}