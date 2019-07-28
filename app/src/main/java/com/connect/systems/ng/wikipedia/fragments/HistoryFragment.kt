package com.connect.systems.ng.wikipedia.fragments


import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.connect.systems.ng.wikipedia.R
import com.connect.systems.ng.wikipedia.WikiApplication
import com.connect.systems.ng.wikipedia.adapters.ArticleListItemRecyclerAdapter
import com.connect.systems.ng.wikipedia.managers.WikiManager
import com.connect.systems.ng.wikipedia.models.WikiPage
import org.jetbrains.anko.alert
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.noButton
import org.jetbrains.anko.yesButton


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class HistoryFragment : Fragment() {
    private var wikiManager : WikiManager? = null
    private var historyRecycler : RecyclerView? = null
    private val adapter = ArticleListItemRecyclerAdapter()
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
        val view = inflater.inflate(R.layout.fragment_history, container, false)
        historyRecycler = view.findViewById(R.id.history_article_recycler)
        historyRecycler!!.layoutManager = LinearLayoutManager(context)
        historyRecycler!!.adapter = adapter
        return view
    }

    override fun onResume() {
        super.onResume()

        // use Anko to do things asynchronously
        doAsync {
            // get the favourites
            val history = wikiManager!!.getHistory()
            // clear the adapter
            adapter.currentResults.clear()
            adapter.currentResults.addAll(history as ArrayList<WikiPage>)
            activity?.runOnUiThread { adapter.notifyDataSetChanged() }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        // inflate the menu we added
        inflater!!.inflate(R.menu.history_menu, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == R.id.action_clear_history) {
            // show confirmation alert
            activity!!.alert("Are you sure you want to clear your history", "Conf") {
                yesButton {
                    // yes was hit
                    // clear history async
                    adapter.currentResults.clear()
                    doAsync {
                        wikiManager?.clearHistory()
                    }
                    activity?.runOnUiThread { adapter.notifyDataSetChanged() }
                }
                noButton {
                    // do something here if you want, but we don't need it
                }
            }.show()
        }

        return true
    }


}
