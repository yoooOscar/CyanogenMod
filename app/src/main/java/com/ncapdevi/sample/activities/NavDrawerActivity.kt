package com.ncapdevi.sample.activities

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.ncapdevi.fragnav.FragNavController
import com.ncapdevi.fragnav.FragNavTransactionOptions
import com.ncapdevi.sample.R
import com.ncapdevi.sample.fragments.*


class NavDrawerActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, BaseFragment.FragmentNavigation {
    //Better convention to properly name the indices what they are in your app


    private var fragNavController: FragNavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav_drawer)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)

        toggle.syncState()

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        val fragments = listOf(
                RecentsFragment.newInstance(0),
                FavoritesFragment.newInstance(0),
                NearbyFragment.newInstance(0),
                FriendsFragment.newInstance(0),
                FoodFragment.newInstance(0),
                RecentsFragment.newInstance(0),
                FavoritesFragment.newInstance(0),
                NearbyFragment.newInstance(0),
                FriendsFragment.newInstance(0),
                FoodFragment.newInstance(0),
                RecentsFragment.newInstance(0),
                FavoritesFragment.newInstance(0))

        fragNavController = FragNavController.newBuilder(savedInstanceState, supportFragmentManager, R.id.container)
                .rootFragments(fragments)
                .defaultTransactionOptions(FragNavTransactionOptions.newBuilder().customAnimations(R.anim.slide_in_from_right, R.anim.slide_out_to_left, R.anim.slide_in_from_left, R.anim.slide_out_to_right).build())
                .build()

    }

    override fun onBackPressed() {
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        when {
            drawer.isDrawerOpen(GravityCompat.START) -> drawer.closeDrawer(GravityCompat.START)
            fragNavController?.isRootFragment?.not() == true -> fragNavController?.popFragment()
            else -> super.onBackPressed()
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        fragNavController?.onSaveInstanceState(outState)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.bb_menu_recents -> fragNavController?.switchTab(INDEX_RECENTS)
            R.id.bb_menu_favorites -> fragNavController?.switchTab(INDEX_FAVORITES)
            R.id.bb_menu_nearby -> fragNavController?.switchTab(INDEX_NEARBY)
            R.id.bb_menu_friends -> fragNavController?.switchTab(INDEX_FRIENDS)
            R.id.bb_menu_food -> fragNavController?.switchTab(INDEX_FOOD)
            R.id.bb_menu_recents2 -> fragNavController?.switchTab(INDEX_RECENTS2)
            R.id.bb_menu_favorites2 -> fragNavController?.switchTab(INDEX_FAVORITES2)
            R.id.bb_menu_nearby2 -> fragNavController?.switchTab(INDEX_NEARBY2)
            R.id.bb_menu_friends2 -> fragNavController?.switchTab(INDEX_FRIENDS2)
            R.id.bb_menu_food2 -> fragNavController?.switchTab(INDEX_FOOD2)
            R.id.bb_menu_recents3 -> fragNavController?.switchTab(INDEX_RECENTS3)
            R.id.bb_menu_favorites3 -> fragNavController?.switchTab(INDEX_FAVORITES3)
        }
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun pushFragment(fragment: Fragment) {
        fragNavController?.pushFragment(fragment)
    }
}
