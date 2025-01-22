package com.mosalab.spacecraftisro.detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.IntentCompat.getParcelableExtra
import com.mosalab.spacecraftisro.R
import com.mosalab.spacecraftisro.core.domain.model.Spacecraft
import com.mosalab.spacecraftisro.databinding.ActivityDetailSpaceraftBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailSpacecraftActivity : AppCompatActivity() {

    private val viewModel: DetailSpacecraftViewModel by viewModel()
    private lateinit var binding: ActivityDetailSpaceraftBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSpaceraftBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val spacecraftData = getParcelableExtra(intent, DATA_KEY, Spacecraft::class.java)
        displaySpacecraftDetails(spacecraftData)
    }

    @SuppressLint("SetTextI18n")
    private fun displaySpacecraftDetails(spacecraft: Spacecraft?) {
        spacecraft?.let {
            supportActionBar?.title = spacecraft.spacecraftId
            binding.contentDetailTourism.tvDetailDescription.text =
                "${spacecraft.name} is a significant asset to India's space research, contributing immensely to scientific advancements."

            var isFavorite = spacecraft.isFavorite
            updateFavoriteStatus(isFavorite)
            binding.fab.setOnClickListener {
                isFavorite = !isFavorite
                viewModel.setFavoriteSpacecraft(spacecraft, isFavorite)
                updateFavoriteStatus(isFavorite)
            }
        }
    }

    private fun updateFavoriteStatus(isFavorite: Boolean) {
        val drawableId = if (isFavorite) R.drawable.ic_favorite_white else R.drawable.ic_not_favorite_white
        binding.fab.setImageDrawable(ContextCompat.getDrawable(this, drawableId))
    }

    companion object {
        const val DATA_KEY = "data_key"
    }
}
