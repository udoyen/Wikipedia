package com.connect.systems.ng.wikipedia.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.connect.systems.ng.wikipedia.R
import com.connect.systems.ng.wikipedia.fragments.ExploreFragment
import com.connect.systems.ng.wikipedia.fragments.FavouritesFragment
import com.connect.systems.ng.wikipedia.fragments.HistoryFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val exploreFragment : ExploreFragment
    private val favouritesFragment : FavouritesFragment
    private val historyFragment : HistoryFragment

    init {
        exploreFragment = ExploreFragment()
        favouritesFragment = FavouritesFragment()
        historyFragment = HistoryFragment()
    }
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        // Setup the switch for each fragment
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)

        when (item.itemId) {
            R.id.navigation_explore -> transaction.replace(R.id.fragment_container, exploreFragment)
            R.id.navigation_history -> transaction.replace(R.id.fragment_container, historyFragment)
            R.id.navigation_favourites -> transaction.replace(R.id.fragment_container, favouritesFragment)
        }
        transaction.commit()
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        nav_view.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        // Setup the default fragment to be shown
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragment_container, exploreFragment)
        transaction.commit()
    }
}
