package com.connect.systems.ng.wikipedia.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.connect.systems.ng.wikipedia.R
import com.connect.systems.ng.wikipedia.holders.CardHolder


class ArticleCardRecyclerAdapter : RecyclerView.Adapter<CardHolder>() {

    /**
     * Used to create the ViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): CardHolder {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        var cardItem = LayoutInflater.from(parent.context).inflate(R.layout.article_card_item, parent, false)
        return CardHolder(cardItem)

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
    override fun onBindViewHolder(p0: CardHolder, p1: Int) {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}