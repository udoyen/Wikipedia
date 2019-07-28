package com.connect.systems.ng.wikipedia

import android.app.Application
import com.connect.systems.ng.wikipedia.managers.WikiManager
import com.connect.systems.ng.wikipedia.providers.ArticleDataProvider
import com.connect.systems.ng.wikipedia.repositories.ArticleDatabaseOpenHelper
import com.connect.systems.ng.wikipedia.repositories.FavouritesRepository
import com.connect.systems.ng.wikipedia.repositories.HistoryRepository

class WikiApplication : Application() {

    private var dbHelper: ArticleDatabaseOpenHelper? = null
    private var favouritesRepository : FavouritesRepository? = null
    private var historyRepository : HistoryRepository? = null
    private var wikiProvider : ArticleDataProvider? = null
    var wikiManager : WikiManager? = null
        private set

    override fun onCreate() {
        super.onCreate()

        dbHelper = ArticleDatabaseOpenHelper(applicationContext)
        favouritesRepository = FavouritesRepository(dbHelper!!)
        historyRepository = HistoryRepository(dbHelper!!)
        wikiProvider = ArticleDataProvider()
        wikiManager = WikiManager(wikiProvider!!, favouritesRepository!!, historyRepository!!)
    }

}