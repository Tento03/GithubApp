package com.example.tes

import android.os.Bundle
import android.view.Menu
import androidx.activity.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.tes.databinding.ActivityMainBinding
import com.example.tes.github.GithubViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<GithubViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_home, R.id.navigation_details, R.id.navigation_followers,R.id.navigation_followings,R.id.navigation_settings))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        return super.onCreateOptionsMenu(menu)
//        menuInflater.inflate(R.menu.menu_gallery,menu)
//        var searchItem=menu?.findItem(R.id.action_search)
//        var searchView=searchItem?.actionView as SearchView
//
//        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                if (query != null) {
//                    viewModel.searchUser(query)
//                }
//                return true
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                return true
//            }
//
//        })
//    }
}