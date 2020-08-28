package rs.sloman.oktomaca.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import rs.sloman.oktomaca.databinding.CommitItemBinding
import rs.sloman.oktomaca.model.CommitBase


class CommitAdapter: ListAdapter<CommitBase, CommitAdapter.CommitBaseViewHolder>(DiffCallback) {


    class CommitBaseViewHolder(private var binding: CommitItemBinding):
        RecyclerView.ViewHolder(binding.root) {

        fun bind(commitBase: CommitBase) {
            binding.commitBase = commitBase
            binding.executePendingBindings()
        }

    }

    companion object DiffCallback : DiffUtil.ItemCallback<CommitBase>() {
        override fun areItemsTheSame(oldItem: CommitBase, newItem: CommitBase): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CommitBase, newItem: CommitBase): Boolean {
            return oldItem.sha == newItem.sha
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommitBaseViewHolder {
       return CommitBaseViewHolder(CommitItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: CommitBaseViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}