package rs.sloman.oktomaca.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import rs.sloman.oktomaca.MainActivity
import rs.sloman.oktomaca.R
import rs.sloman.oktomaca.adapter.RepoGridAdapter
import rs.sloman.oktomaca.databinding.FragmentProfileBinding
import rs.sloman.oktomaca.viewmodel.MainViewModel

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as MainActivity?)?.supportActionBar?.title = "Profile"

        val binding = FragmentProfileBinding.inflate(layoutInflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel

        binding.rvRepos.adapter = RepoGridAdapter(RepoGridAdapter.OnClickListener{
            //TODO click handling
            Toast.makeText(requireContext(), it.name, Toast.LENGTH_SHORT).show()
        })

        return binding.root
    }
}