package rs.sloman.oktomaca.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import rs.sloman.oktomaca.R
import rs.sloman.oktomaca.databinding.FragmentProfileBinding
import rs.sloman.oktomaca.repo.Repo
import rs.sloman.oktomaca.viewmodel.MainViewModel
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val viewModel: MainViewModel by viewModels()

    @Inject
    lateinit var repo: Repo


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentProfileBinding.inflate(layoutInflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel

        setHasOptionsMenu(true)

        return binding.root
    }
}