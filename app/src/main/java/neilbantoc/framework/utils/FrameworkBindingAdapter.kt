package neilbantoc.framework.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

@BindingAdapter("data")
fun <T> setRecyclerViewProperties(recyclerView: RecyclerView, data: T) {
    if (recyclerView.adapter is BindableAdapter<*>) {
        @Suppress("UNCHECKED_CAST")
        (recyclerView.adapter as BindableAdapter<T>).setData(data)
    }
}

@BindingAdapter("imageUrl")
fun <T> setImageUrl(imageView: ImageView, url: String) {
    Picasso.get().load(url).into(imageView)
}

@BindingAdapter("circularImageUrl")
fun <T> setCircularImageUrl(imageView: ImageView, url: String) {
    Picasso.get().load(url).transform(CircleTransform()).into(imageView)
}