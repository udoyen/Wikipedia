package com.connect.systems.ng.wikipedia.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.connect.systems.ng.wikipedia.R
import com.connect.systems.ng.wikipedia.holders.ListItemHolder
import com.connect.systems.ng.wikipedia.models.WikiPage


class ArticleListItemRecyclerAdapter : RecyclerView.Adapter<ListItemHolder>() {

     val currentResults: ArrayList<WikiPage> = ArrayList()
    /**
     * Used to create the ViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ListItemHolder {
        val listItem = LayoutInflater.from(parent.context).inflate(R.layout.article_list_item, parent, false)
        return ListItemHolder(listItem)

    }

    /**
     * The number of items to be held by
     * the recycler view
     */
    override fun getItemCount(): Int {
        return currentResults.size
    }

    /**
     * Use this to update the ViewHolder content
     * with new content from the page
     */
    override fun onBindViewHolder(holder: ListItemHolder, position: Int) {

        var page = currentResults[position]
        holder.updateWithPage(page)
    }

}