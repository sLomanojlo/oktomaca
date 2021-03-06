package rs.sloman.oktomaca.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint
import rs.sloman.oktomaca.MainActivity
import rs.sloman.oktomaca.R
import rs.sloman.oktomaca.adapter.RepoGridAdapter
import rs.sloman.oktomaca.databinding.FragmentProfileBinding
import rs.sloman.oktomaca.network.Status
import rs.sloman.oktomaca.util.Constants
import rs.sloman.oktomaca.viewmodel.ProfileViewModel


/**Initial Fragment that shows the Profile and the Repos of the user Octocat.
 * */
@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as MainActivity?)?.supportActionBar?.title = getString(R.string.profile)

        val binding = FragmentProfileBinding.inflate(layoutInflater)

        /** Allows Data Binding to Observe LiveData with the lifecycle of this Fragment */
        binding.lifecycleOwner = this

        /** Giving the binding access to the OverviewViewModel  */
        binding.viewModel = viewModel

        binding.rvRepos.adapter = RepoGridAdapter(RepoGridAdapter.OnClickListener{
            val bundle = Bundle().apply{ putString(Constants.REPO_NAME, it.name)}

            Navigation.findNavController(binding.rvRepos).navigate(
                R.id.action_profileFragment_to_commitsFragment, bundle)
        })

        /** Observer for status that enables a retry button for fetching remote data. */
        viewModel.status.observe(viewLifecycleOwner, Observer {
            if (viewModel.status.value == Status.ERROR) {
                binding.ivStatus.isEnabled = true
                binding.ivStatus.setOnClickListener {
                    viewModel.getProfile()
                }
            } else {
                binding.ivStatus.isEnabled = false
            }
        })

        return binding.root
    }
}