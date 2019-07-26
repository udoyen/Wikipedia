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
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ListItemHolder {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        val listItem = LayoutInflater.from(parent.context).inflate(R.layout.article_list_item, parent, false)
        return ListItemHolder(listItem)

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