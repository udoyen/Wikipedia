package com.connect.systems.ng.wikipedia.activities

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.connect.systems.ng.wikipedia.R
import com.connect.systems.ng.wikipedia.adapters.ArticleListItemRecyclerAdapter
import com.connect.systems.ng.wikipedia.providers.ArticleDataProvider
import kotlinx.android.synthetic.main.activity_article_detail.*
import kotlinx.android.synthetic.main.activity_article_detail.toolbar
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    private val articleProvider: ArticleDataProvider = ArticleDataProvider()
    var adapter: ArticleListItemRecyclerAdapter = ArticleListItemRecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        // set the toolbar from the layout file
        setSupportActionBar(toolbar)
        // Reference to the supportActionBar in the layout file
        // set the action button on the left side of the toolbar
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        // setup the adapter
        search_results_recycler.layoutManager = LinearLayoutManager(this)
        search_results_recycler.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        // When the home item is selected finish the
        // the activity and go back to the previous one
        if (item!!.itemId == android.R.id.home) {
            finish()
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu with the one we created
        menuInflater.inflate(R.menu.search_menu, menu)
        // Get a reference to our created search menu
        val searchItem = menu!!.findItem(R.id.action_search)

        // Get a reference to the Search Manager service
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        // Get a reference to the search view
        val searchView = searchItem!!.actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(this.componentName))
        searchView.setIconifiedByDefault(false)
        searchView.requestFocus()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {
                // Do the search and update the elements
                articleProvider.search(query, 0, 20) { wikiResult ->
                    adapter.currentResults.clear()
                    adapter.currentResults.addAll(wikiResult.query!!.pages)
                    runOnUiThread { adapter.notifyDataSetChanged() }
                }
//                println("updated search")
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        return super.onCreateOptionsMenu(menu)

    }

}
