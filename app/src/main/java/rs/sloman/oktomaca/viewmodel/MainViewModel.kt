package rs.sloman.oktomaca.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import rs.sloman.oktomaca.repo.Repo


class MainViewModel @ViewModelInject constructor(val repo: Repo) : ViewModel() {

    val profile =  viewModelScope.launch {
        val response = repo.getProfile()

        if(response.isSuccessful)
        Log.d("Slobodan", response.body().toString())
    }


}