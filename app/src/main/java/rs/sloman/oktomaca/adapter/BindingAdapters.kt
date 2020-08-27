package rs.sloman.oktomaca.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import rs.sloman.oktomaca.R
import rs.sloman.oktomaca.network.Status


@BindingAdapter("bindId")
fun bindId(textView: TextView, id: Int) {
    textView.text = id.toString()
}

@BindingAdapter("bindTextView")
fun bindTextView(textView: TextView, text: String?) {
    textView.text = text
}

@BindingAdapter("bindText")
fun bindImage(textView: TextView, text: String?) {
    textView.text = text
}

/**
 * Uses the Glide library to load an image by URL into an [ImageView]
 */
@BindingAdapter("bindImage")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .circleCrop()
            .apply(
                RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image))
            .into(imgView)
    }
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