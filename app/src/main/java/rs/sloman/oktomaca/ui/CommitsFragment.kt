package rs.sloman.oktomaca.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import rs.sloman.oktomaca.MainActivity
import rs.sloman.oktomaca.R
import rs.sloman.oktomaca.databinding.FragmentCommitsBinding
import rs.sloman.oktomaca.viewmodel.CommitsViewModel

@AndroidEntryPoint
class CommitsFragment : Fragment(R.layout.fragment_commits) {

    private val viewModel: CommitsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as MainActivity?)?.supportActionBar?.title = "Commits"

        val binding = FragmentCommitsBinding.inflate(layoutInflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel

        return super.onCreateView(inflater, container, savedInstanceState)

    }

}