package rs.sloman.oktomaca.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import rs.sloman.oktomaca.repo.Repo


class CommitsViewModel @ViewModelInject constructor(val repo: Repo) : ViewModel() {

}