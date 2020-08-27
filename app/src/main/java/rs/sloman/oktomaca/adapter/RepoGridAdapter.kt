package rs.sloman.oktomaca.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import rs.sloman.oktomaca.databinding.RepoItemBinding
import rs.sloman.oktomaca.model.UserRepo


class RepoGridAdapter(private val onClickListener: OnClickListener)
    : ListAdapter<UserRepo, RepoGridAdapter.UserRepoViewHolder>(DiffCallback)
{



    class UserRepoViewHolder(private var binding: RepoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(userRepo: UserRepo) {
            binding.repo = userRepo
            binding.executePendingBindings()
        }

    }

    companion object DiffCallback : DiffUtil.ItemCallback<UserRepo>() {
        override fun areItemsTheSame(oldItem: UserRepo, newItem: UserRepo): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: UserRepo, newItem: UserRepo): Boolean {
            return oldItem.id == newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserRepoViewHolder {
        return UserRepoViewHolder((RepoItemBinding.inflate(LayoutInflater.from(parent.context))))
    }

    override fun onBindViewHolder(holder: UserRepoViewHolder, position: Int) {
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