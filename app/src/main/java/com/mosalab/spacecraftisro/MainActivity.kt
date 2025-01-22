package com.mosalab.spacecraftisro

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.mosalab.spacecraftisro.databinding.ActivityMainBinding
import com.mosalab.spacecraftisro.home.HomeFragment

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var drawer: DrawerLayout

    private lateinit var broadcastReceiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarMain.toolbar)

        drawer = binding.drawerLayout

        val toggle = ActionBarDrawerToggle(
            this,
            drawer,
            binding.appBarMain.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        binding.navView.setNavigationItemSelectedListener(this)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, HomeFragment())
                .commit()
            supportActionBar?.title = getString(R.string.app_name)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment: Fragment? = null
        var title = getString(R.string.app_name)

        when (item.itemId) {
            R.id.nav_home -> {
                fragment = HomeFragment()
                title = getString(R.string.app_name)
            }
            R.id.nav_favorite -> {
                val uri = Uri.parse("spacecraftisro://favorite")
                startActivity(Intent(Intent.ACTION_VIEW, uri))
            }
        }

        fragment?.let {
            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, it)
                .commit()
        }

        supportActionBar?.title = title
        drawer.closeDrawer(GravityCompat.START)
        return true
    }
    private fun registerBroadCastReceiver() {
        broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                val action = intent.action
                if (action == Intent.ACTION_BATTERY_LOW) {
                    println("Battery is low!")
                }
            }
        }

        val intentFilter = IntentFilter(Intent.ACTION_BATTERY_LOW)
        registerReceiver(broadcastReceiver, intentFilter)
    }
    override fun onStart() {
        super.onStart()
        registerBroadCastReceiver()
    }
    override fun onStop() {
        super.onStop()
        unregisterReceiver(broadcastReceiver)
    }
}
