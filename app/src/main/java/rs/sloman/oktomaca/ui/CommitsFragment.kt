package rs.sloman.oktomaca.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import rs.sloman.oktomaca.MainActivity
import rs.sloman.oktomaca.R
import rs.sloman.oktomaca.adapter.CommitAdapter
import rs.sloman.oktomaca.databinding.FragmentCommitsBinding
import rs.sloman.oktomaca.network.Status
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

        //Obtained repoName from bundle
        viewModel.repoName.value = arguments?.getString("repoName")

        binding.rvCommits.setHasFixedSize(true)

        binding.rvCommits.adapter = CommitAdapter()

        viewModel.repoName.observe(viewLifecycleOwner, Observer { repoName ->
            if (repoName.isNotEmpty()) viewModel.getCommitsBase(repoName)
        })

        viewModel.status.observe(viewLifecycleOwner, Observer {
            if (viewModel.status.value == Status.ERROR) {
                binding.ivStatusCommits.isEnabled = true
                binding.ivStatusCommits.setOnClickListener {
                    viewModel.repoName.value?.let {
                        viewModel.getCommitsBase(it)
                    }

                }
            } else {
                binding.ivStatusCommits.isEnabled = false
            }
        })


        return binding.root

    }

}