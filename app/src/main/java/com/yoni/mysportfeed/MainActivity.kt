package com.yoni.mysportfeed

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.yoni.mysportfeed.data.feed.IFeedRepository
import com.yoni.mysportfeed.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    internal lateinit var feedRepository: IFeedRepository

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val splash = installSplashScreen()

        splash.setKeepOnScreenCondition {
            !feedRepository.isReady()
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            feedRepository.refreshFeedItems()
        }

        navController = findNavController(R.id.nav_host_fragment_activity_main)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id) {
                R.id.navigation_story -> fullScreenMode(true)
                else -> fullScreenMode(false)
            }
        }

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)
    }

    @SuppressLint("RestrictedApi")
    private fun fullScreenMode(enabled: Boolean) {
        when(enabled) {
            true -> {
                supportActionBar?.setShowHideAnimationEnabled(false)
                binding.navView.visibility = View.GONE
                supportActionBar?.hide()
            }
            else -> {
                supportActionBar?.setShowHideAnimationEnabled(true)
                binding.navView.visibility = View.VISIBLE
                supportActionBar?.show()
            }
        }
    }
}