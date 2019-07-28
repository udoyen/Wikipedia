package com.connect.systems.ng.wikipedia.activities

import android.net.http.SslError
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.webkit.SslErrorHandler
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.connect.systems.ng.wikipedia.R
import com.connect.systems.ng.wikipedia.WikiApplication
import com.connect.systems.ng.wikipedia.managers.WikiManager
import com.connect.systems.ng.wikipedia.models.WikiPage
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_article_detail.*
import org.jetbrains.anko.toast
import java.lang.Exception

class ArticleDetailActivity : AppCompatActivity() {
    private var wikiManager: WikiManager? = null
    private var currentPage: WikiPage? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_detail)

        //instantiate
        wikiManager = (applicationContext as WikiApplication).wikiManager

        // set the toolbar from the layout file
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)


        // get the page from the extras
        val wikiPageJson = intent.getStringExtra("page")
        currentPage = Gson().fromJson(wikiPageJson, WikiPage::class.java)

        // update the toolbar title with the title
        // from the wiki title
        supportActionBar?.title = currentPage?.title

        // this will ensure the url is loaded rather than
        // calling over to the WebView in some versions
        // of android
        article_detail_webview?.webViewClient = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                return false
            }

            override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
                handler?.proceed()
            }
        }

        article_detail_webview.loadUrl(currentPage!!.fullurl)

        // this will be added into the local
        // repository
        wikiManager?.addHistory(currentPage!!)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.article_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        } else if (item.itemId == R.id.action_favourites) {
            try {
                // determine if the article is already a favourite or not
                if (wikiManager!!.getIsFavourite(currentPage?.pageid!!)) {
                    wikiManager!!.removeFavourite(currentPage!!.pageid!!)
                    toast("Article removed from favourites")

                } else {
                    wikiManager!!.addFavourite(currentPage!!)
                    toast("Article added to favourites")
                }

            } catch (ex: Exception) {
                toast("Unable to update this article")
            }

        }
        return true

    }
}
