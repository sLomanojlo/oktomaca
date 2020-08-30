package rs.sloman.oktomaca.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import rs.sloman.oktomaca.databinding.RepoItemBinding
import rs.sloman.oktomaca.model.UserRepo

/** This class implements a RecyclerView which uses Data Binding to show a list of data,
 * including computing diffs between items with the DiffCallback */
class RepoGridAdapter(private val onClickListener: OnClickListener)
    : ListAdapter<UserRepo, RepoGridAdapter.RepoViewHolder>(DiffCallback)
{

    /** The RepoViewHolder constructor takes the binding variable from the UserRepo,
     * giving that way access to the full data of the repo*/
    class RepoViewHolder(private var binding: RepoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(userRepo: UserRepo) {
            binding.repo = userRepo
            binding.executePendingBindings()
        }

    }

    /** Enables a show diff function to the RecyclerView
     * when the list of CommitBase has been updated*/
    companion object DiffCallback : DiffUtil.ItemCallback<UserRepo>() {
        override fun areItemsTheSame(oldItem: UserRepo, newItem: UserRepo): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: UserRepo, newItem: UserRepo): Boolean {
            return oldItem.id == newItem.id
        }

    }

    /** Create a new RecyclerView item*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        return RepoViewHolder((RepoItemBinding.inflate(LayoutInflater.from(parent.context))))
    }

    /** Replaces dynamically the contents of a view when scrolling occurs.*/
    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val userRepo = getItem(position)
        holder.itemView.setOnClickListener{
            onClickListener.onClick(userRepo)
        }
        holder.bind(userRepo)
    }

    class OnClickListener(val clickListener: (userRepo: UserRepo) -> Unit){
        fun onClick(userRepo: UserRepo) = clickListener(userRepo)
    }
}