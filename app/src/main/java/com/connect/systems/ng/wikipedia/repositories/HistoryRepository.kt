package com.connect.systems.ng.wikipedia.repositories

import com.connect.systems.ng.wikipedia.models.WikiPage
import com.connect.systems.ng.wikipedia.models.WikiThumbnail
import com.google.gson.Gson
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.rowParser
import org.jetbrains.anko.db.select

class HistoryRepository(val databaseHelper: ArticleDatabaseOpenHelper) {
    private val TABLE_NAME: String = "History"

    fun addFavourite(page: WikiPage) {
        databaseHelper.use {
            insert(TABLE_NAME,
                "id" to page.pageid,
                "title" to page.title,
                "url" to page.fullurl,
                "thumbnailJson" to Gson().toJson(page.thumbnail))
        }


    }

    fun removePageById(pageId: Int) {
        // don't leaves spaces in the variable name {pageId}
        databaseHelper.use {
            delete(TABLE_NAME, "id = {pageId}", "pageId" to pageId)
        }
    }



    // use this to poll the items from memory
    fun getAllHistory() : ArrayList<WikiPage> {
        val pages = ArrayList<WikiPage>()

        // use this to tell Anko how to map models to the
        // database tables
        val articleRowParser = rowParser { id: Int, title: String, url: String, thumbnailJson: String ->
            val page = WikiPage()
            page.title = title
            page.pageid = id
            page.fullurl = url
            page.thumbnail = Gson().fromJson(thumbnailJson, WikiThumbnail::class.java)

            pages.add(page)
        }

        // use the row parser
        databaseHelper.use {
            select(TABLE_NAME).parseList(articleRowParser)
        }
        return pages
    }
}