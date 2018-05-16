package org.jw.territory

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_map -> {

                replaceFragment(MapsFragment.newInstance("", ""))

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_territories -> {
                replaceFragment(TerritoryFragment.newInstance("", ""))
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        replaceFragment(MapsFragment.newInstance("", ""))

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private fun replaceFragment(target: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.frameRoot, target, "home")
                .commit()
    }
}
