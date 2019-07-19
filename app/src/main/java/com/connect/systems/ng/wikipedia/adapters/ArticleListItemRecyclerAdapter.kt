package com.connect.systems.ng.wikipedia.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.connect.systems.ng.wikipedia.R
import com.connect.systems.ng.wikipedia.holders.ListItemHolder


class ArticleListItemRecyclerAdapter : RecyclerView.Adapter<ListItemHolder>() {

    /**
     * Used to create the ViewHolder
     */
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ListItemHolder {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        var cardItem = LayoutInflater.from(p0.context).inflate(R.layout.article_list_item, p0, false)
        return ListItemHolder(cardItem)

    }

    /**
     * The number of items to be held by
     * the recycler view
     */
    override fun getItemCount(): Int {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return 15
    }

    /**
     * Use this to update the ViewHolder content
     * with new content from the page
     */
    override fun onBindViewHolder(p0: ListItemHolder, p1: Int) {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}