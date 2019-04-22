package neilbantoc.affirm.features.search

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import neilbantoc.affirm.BR
import neilbantoc.affirm.R
import neilbantoc.affirm.data.model.Photo
import neilbantoc.affirm.databinding.ActivitySearchBinding
import neilbantoc.affirm.databinding.ItemPhotoGridBinding
import neilbantoc.framework.base.BaseView
import neilbantoc.framework.utils.BindableAdapter

class SearchView : SearchContract.View, BaseView<SearchState, SearchContract.View.ViewActions, ActivitySearchBinding>(actions= SearchContract.View.ViewActions(), resId = R.layout.activity_search) {

    val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            actions.scroll.publish(dy)
        }
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            val manager = recyclerView.layoutManager as StaggeredGridLayoutManager
            val lastVisibleItem = manager.findLastVisibleItemPositions(IntArray(manager.spanCount)).last()
            val totalItems = manager.itemCount
            actions.itemsTillEnd.publish(totalItems - lastVisibleItem)
        }
    }

    override fun initView(context: Context) {
        super.initView(context)
        dataBinding.recyclerView.adapter = PhotoAdapter()
        dataBinding.recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        dataBinding.recyclerView.addOnScrollListener(scrollListener)
    }

    class PhotoAdapter() : BindableAdapter<Collection<Photo>?>, RecyclerView.Adapter<PhotoAdapter.Holder>() {

        private val items = LinkedHashSet<Photo>()

        override fun setData(data: Collection<Photo>?) {
            data?.apply {
                items.addAll(data)
                notifyDataSetChanged()
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            val inflater = LayoutInflater.from(parent.context)
            val dataBinding = DataBindingUtil.inflate<ItemPhotoGridBinding>(inflater, R.layout.item_photo_grid, parent, false)
            return Holder(dataBinding)
        }

        override fun getItemCount(): Int {
            return items.size
        }

        override fun onBindViewHolder(holder: Holder, position: Int) {
            holder.bind(items.elementAt(position))
        }

        class Holder(val dataBinding: ViewDataBinding) : RecyclerView.ViewHolder(dataBinding.root) {
            fun bind(photo: Photo) {
                dataBinding.setVariable(BR.photo, photo)
                dataBinding.executePendingBindings()
            }
        }
    }
}