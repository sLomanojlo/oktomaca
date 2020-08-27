package rs.sloman.oktomaca.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import rs.sloman.oktomaca.R
import rs.sloman.oktomaca.repo.Repo
import rs.sloman.oktomaca.viewmodel.MainViewModel
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val viewModel: MainViewModel by viewModels()

    @Inject
    lateinit var repo: Repo


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        GlobalScope.launch(Dispatchers.IO) {
            val response = repo.getProfile()

            if (response.isSuccessful) {
                Log.d("Slobodan", response.body().toString())
            }
        }
    }

}