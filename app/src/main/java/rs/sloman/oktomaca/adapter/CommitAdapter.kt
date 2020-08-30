package rs.sloman.oktomaca.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import rs.sloman.oktomaca.databinding.CommitItemBinding
import rs.sloman.oktomaca.model.CommitBase


/** This class implements a RecyclerView which uses Data Binding to show a list of data,
 * including computing diffs between items with the DiffCallback */
class CommitAdapter: ListAdapter<CommitBase, CommitAdapter.CommitBaseViewHolder>(DiffCallback) {


    /** The CommitViewHolder constructor takes the binding variable from the CommitViewItem,
     * giving that way access to the full data of the commit*/
    class CommitBaseViewHolder(private var binding: CommitItemBinding):
        RecyclerView.ViewHolder(binding.root) {

        fun bind(commitBase: CommitBase) {
            binding.commitBase = commitBase
            binding.executePendingBindings()
        }

    }

    /** Enables a show diff function to the RecyclerView
     * when the list of CommitBase has been updated*/
    companion object DiffCallback : DiffUtil.ItemCallback<CommitBase>() {
        override fun areItemsTheSame(oldItem: CommitBase, newItem: CommitBase): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CommitBase, newItem: CommitBase): Boolean {
            return oldItem.sha == newItem.sha
        }

    }

    /** Create a new RecyclerView item*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommitBaseViewHolder {
       return CommitBaseViewHolder(CommitItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    /** Replaces dynamically the contents of a view when scrolling occurs.*/
    override fun onBindViewHolder(holder: CommitBaseViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}