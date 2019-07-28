package com.connect.systems.ng.wikipedia.fragments


import android.content.Context
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.connect.systems.ng.wikipedia.R
import com.connect.systems.ng.wikipedia.WikiApplication
import com.connect.systems.ng.wikipedia.adapters.ArticleCardRecyclerAdapter
import com.connect.systems.ng.wikipedia.managers.WikiManager
import com.connect.systems.ng.wikipedia.models.WikiPage
import org.jetbrains.anko.doAsync


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class FavouritesFragment : Fragment() {
    private var wikiManager : WikiManager? = null
    private var favourites : RecyclerView? = null
    private val adapter : ArticleCardRecyclerAdapter = ArticleCardRecyclerAdapter()

    /**
     * used to instantiate the WikiManager
     * as this isn't an activity
     */
    override fun onAttach(context: Context?) {
        super.onAttach(context)

        wikiManager = (activity!!.applicationContext as WikiApplication).wikiManager
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favourites, container, false)

        favourites = view.findViewById(R.id.favourites_article_recycler)
        // set the layoutmanager
        favourites!!.layoutManager = LinearLayoutManager(context)
        // set the adapter
        favourites!!.adapter = adapter

        return view
    }

    override fun onResume() {
        super.onResume()

        // use Anko to do things asynchronously
        doAsync {
            // get the favourites
            val favouriteArticles = wikiManager!!.getFavourites()
            // clear the adapter
            adapter.currentResults.clear()
            adapter.currentResults.addAll(favouriteArticles as ArrayList<WikiPage>)
            activity?.runOnUiThread { adapter.notifyDataSetChanged() }

        }
    }
}
