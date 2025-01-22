package com.mosalab.spacecraftisro.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mosalab.spacecraftisro.core.ui.SpacecraftAdapter
import com.mosalab.spacecraftisro.favorite.databinding.ActivityFavoriteBinding
import com.mosalab.spacecraftisro.detail.DetailSpacecraftActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules



class FavoriteActivity : AppCompatActivity() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Correctly inflate layout with ViewBinding
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Load Koin modules
        loadKoinModules(favoriteModule)

        supportActionBar?.title = "List Favorite Spacecraft"



        // Setup RecyclerView
        val spacecraftAdapter = SpacecraftAdapter().apply {
            onItemClick = { selectedData ->
                val intent = Intent(this@FavoriteActivity, DetailSpacecraftActivity::class.java)
                intent.putExtra(DetailSpacecraftActivity.DATA_KEY, selectedData)
                startActivity(intent)
            }
        }

        favoriteViewModel.favoriteSpacecraft.observe(this) { dataSpacecraft ->
            spacecraftAdapter.submitList(dataSpacecraft)

            // Toggle views visibility
            if (dataSpacecraft.isNotEmpty()) {
                binding.lottieAnimationView.visibility = View.GONE
                binding.rvSpacecraft.visibility = View.VISIBLE
            } else {
                binding.lottieAnimationView.visibility = View.VISIBLE
                binding.rvSpacecraft.visibility = View.GONE
            }
        }

        with(binding.rvSpacecraft) {
            layoutManager = LinearLayoutManager(this@FavoriteActivity)
            setHasFixedSize(true)
            adapter = spacecraftAdapter
        }
    }
}
