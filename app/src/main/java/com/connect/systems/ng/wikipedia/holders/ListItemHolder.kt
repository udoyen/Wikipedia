package com.connect.systems.ng.wikipedia.holders

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.connect.systems.ng.wikipedia.R
import com.connect.systems.ng.wikipedia.activities.ArticleDetailActivity
import com.connect.systems.ng.wikipedia.models.WikiPage
import com.google.gson.Gson
import com.squareup.picasso.Picasso

class ListItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val listArticleImageView : ImageView = itemView.findViewById(R.id.result_icon)
    private val titleTextView : TextView = itemView.findViewById(R.id.result_title)

    private var currentPage: WikiPage? = null

    init {
        // create on clicklistener
        // can also be created in the adapter
        // use this to pass the article detail to
        // the ArticleDetailActivity
        itemView.setOnClickListener { view: View? ->
            val detailPageIntent = Intent(itemView.context, ArticleDetailActivity::class.java)
            val pageJson = Gson().toJson(currentPage)
            detailPageIntent.putExtra("page", pageJson)
            itemView.context.startActivity(detailPageIntent)
        }
    }

    fun updateWithPage(page: WikiPage) {
        currentPage = page
        titleTextView.text = page.title

        if (page.thumbnail != null)
            Picasso.get().load(page.thumbnail!!.source).into(listArticleImageView)

    }
}