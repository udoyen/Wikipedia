package com.connect.systems.ng.wikipedia.holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.connect.systems.ng.wikipedia.R

class ListItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val listItemImageView : ImageView = itemView.findViewById<ImageView>(R.id.result_icon)
    private val listItemTitleTextView : TextView = itemView.findViewById<TextView>(R.id.result_title)
}