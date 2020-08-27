package rs.sloman.oktomaca.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import rs.sloman.oktomaca.R
import rs.sloman.oktomaca.network.Status


@BindingAdapter("bindId")
fun bindId(textView: TextView, id: Int) {
    textView.text = id.toString()
}

@BindingAdapter("bindStatus")
fun bindStatus(imageView: ImageView, status: Status) {

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