package rs.sloman.oktomaca.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import rs.sloman.oktomaca.model.CommitBase
import rs.sloman.oktomaca.network.Status
import rs.sloman.oktomaca.repo.Repo


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
            try {
                _commitsBase.value = repoName?.let { repo.getCommits(it).body() }
                _status.value = Status.DONE
            } catch (e: Exception) {
                _status.value = Status.ERROR
            }
        }

    }

}