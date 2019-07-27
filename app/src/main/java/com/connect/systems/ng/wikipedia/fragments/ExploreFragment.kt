package com.connect.systems.ng.wikipedia.fragments


import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.connect.systems.ng.wikipedia.R
import com.connect.systems.ng.wikipedia.activities.SearchActivity
import com.connect.systems.ng.wikipedia.adapters.ArticleCardRecyclerAdapter
import com.connect.systems.ng.wikipedia.models.WikiResult
import com.connect.systems.ng.wikipedia.providers.ArticleDataProvider
import java.lang.Exception


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ExploreFragment : Fragment() {
    private val articleProvider: ArticleDataProvider = ArticleDataProvider()
    private var searchCardView: CardView? = null
    private var exploreRecycler: RecyclerView? = null
    private var refresher: SwipeRefreshLayout? = null
    var adapter: ArticleCardRecyclerAdapter = ArticleCardRecyclerAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_explore, container, false)

        searchCardView = view.findViewById(R.id.explore_card_view)
        exploreRecycler = view.findViewById(R.id.explore_article_recycler)
        refresher = view.findViewById(R.id.refresher)

        searchCardView!!.setOnClickListener {
            val searchIntent = Intent(context, SearchActivity::class.java)
            context!!.startActivity(searchIntent)
        }
        exploreRecycler!!.layoutManager = LinearLayoutManager(context)
        exploreRecycler!!.adapter = adapter

        refresher?.setOnRefreshListener {
            getRandomArticles()
        }

        // Load on page start
        getRandomArticles()

        return view


    }

    private fun getRandomArticles() {
        refresher?.isRefreshing = true

        try {
            articleProvider.getRandom(15) { wikiResult ->
                adapter.currentResults.clear()
                adapter.currentResults.addAll(wikiResult.query!!.pages)
                activity!!.runOnUiThread {
                    adapter.notifyDataSetChanged()
                    // tell the refresher that you done
                    // refreshing
                    refresher?.isRefreshing = false
                }

            }
        } catch (ex: Exception) {
            // Show in the UI
            val builder = AlertDialog.Builder(activity)
            builder.setMessage(ex.message).setTitle("oops!")
            val dialog = builder.create()
            dialog.show()
        }
    }


}
