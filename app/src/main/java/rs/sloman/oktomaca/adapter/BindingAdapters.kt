package rs.sloman.oktomaca.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import rs.sloman.oktomaca.R
import rs.sloman.oktomaca.model.CommitBase
import rs.sloman.oktomaca.model.UserRepo
import rs.sloman.oktomaca.network.Status
import java.text.SimpleDateFormat
import java.util.*

/** Binds String with TextView */
@BindingAdapter("bindTextView")
fun bindTextView(textView: TextView, text: String?) {
    textView.text = text
}


/** Uses the Glide library to load an image by URL into an [ImageView] */
@BindingAdapter("bindImage")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    if (imgUrl != null) {

        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .circleCrop()
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(imgView)
        imgView.visibility = View.VISIBLE
    } else {
        imgView.visibility = View.GONE
    }
}

/** Binds Status with TextView */
@BindingAdapter("bindStatus")
fun bindStatus(imageView: ImageView, status: Status?) {

    when (status) {
        Status.LOADING -> {
            imageView.setImageResource(R.drawable.loading_animation)
            imageView.visibility = View.VISIBLE
        }
        Status.ERROR -> {
            imageView.setImageResource(R.drawable.ic_connection_error)
            imageView.visibility = View.VISIBLE
        }
        Status.DONE -> {
            imageView.visibility = View.GONE
        }

    }
}

/** Binds List<UserRepo> with TextView */
@BindingAdapter("bindRepos")
fun bindReposRecyclerView(recyclerView: RecyclerView, data: List<UserRepo>?) {
    if (data.isNullOrEmpty()) {
        recyclerView.visibility = View.GONE
    } else {
        recyclerView.visibility = View.VISIBLE
        val adapter = recyclerView.adapter as RepoGridAdapter
        adapter.submitList(data)
    }
}

/** Binds List<CommitBase> with RecyclerView */
@BindingAdapter("bindCommits")
fun bindCommitsRecyclerView(recyclerView: RecyclerView, data: List<CommitBase>?) {
    if (data.isNullOrEmpty()) {
        recyclerView.visibility = View.GONE
    } else {
        recyclerView.visibility = View.VISIBLE
        val adapter = recyclerView.adapter as CommitAdapter
        adapter.submitList(data)
    }
}

/** Binds Date with TextView */
@BindingAdapter("bindDate")
fun bindDate(textView: TextView, date: Date?) {
    date?.let {
        textView.text = SimpleDateFormat("dd/MM/yyyy").format(date)
    }
}

/** Binds Open issues with TextView */
@BindingAdapter("bindOpenIssues")
fun bindOpenIssues(textView: TextView, openIssues: Int) {
    textView.text = if(openIssues > 0) "Open issues: $openIssues" else "No open issues"
}