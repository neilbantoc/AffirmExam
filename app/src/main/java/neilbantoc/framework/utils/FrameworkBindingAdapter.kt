package neilbantoc.framework.utils

import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
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

@BindingAdapter("constraintRatioString")
fun <T> setRatio(imageView: ImageView, ratio: String) {
    val set = ConstraintSet()
    var constraintLayout: ConstraintLayout? = null
    var temp: View = imageView
    while (constraintLayout == null) {
        if (temp.parent == null) {
            return
        } else {
            if (temp.parent is ConstraintLayout) {
                constraintLayout = temp.parent as ConstraintLayout
            } else {
                temp = temp.parent as View
            }
        }
    }
    set.clone(constraintLayout)
    set.setDimensionRatio(imageView.id, ratio)
    set.applyTo(constraintLayout)
}

