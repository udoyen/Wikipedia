package com.connect.systems.ng.wikipedia.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.connect.systems.ng.wikipedia.R
import com.connect.systems.ng.wikipedia.holders.CardHolder
import com.connect.systems.ng.wikipedia.models.WikiPage


class ArticleCardRecyclerAdapter : RecyclerView.Adapter<CardHolder>() {
    val currentResults: ArrayList<WikiPage> = ArrayList()

    /**
     * Used to create the ViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): CardHolder {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        val cardItem = LayoutInflater.from(parent.context).inflate(R.layout.article_card_item, parent, false)
        return CardHolder(cardItem)

    }

    /**
     * The number of items to be held by
     * the recycler view
     */
    override fun getItemCount(): Int {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return currentResults.size
    }

    /**
     * Use this to update the ViewHolder content
     * with new content from the page
     */
    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        var page = currentResults[position]
        // update view within holder
        holder.updateWithPage(page)
    }

}