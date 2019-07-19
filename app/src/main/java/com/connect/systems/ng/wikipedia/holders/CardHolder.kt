package com.connect.systems.ng.wikipedia.holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.connect.systems.ng.wikipedia.R

class CardHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val articleImageView : ImageView = itemView.findViewById<ImageView>(R.id.article_image)
    private val titleTextView : TextView = itemView.findViewById<TextView>(R.id.article_title)
}