package com.farhanrv.iphonecatalogue.ui.detail

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.farhanrv.iphonecatalogue.R
import com.farhanrv.iphonecatalogue.core.data.Resource
import com.farhanrv.iphonecatalogue.core.domain.model.Iphone
import com.farhanrv.iphonecatalogue.databinding.ActivityDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_slug"
    }

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = null
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val iphone = intent.getParcelableExtra<Iphone>(EXTRA_DATA)

        showDetail(iphone)
    }

    private fun showDetail(phoneSlug: Iphone?) {
        phoneSlug?.let { iphone ->
            viewModel.getDetail(phoneSlug.slug).observe(this) { detail ->
                detail?.let {
                    when (detail) {
                        is Resource.Loading -> {
                            with(binding) {
                                loadingCover.visibility = View.VISIBLE
                                progressBar.visibility = View.VISIBLE
                                fab.visibility = View.GONE
                            }

                        }
                        is Resource.Success -> {
                            val data = detail.data!![0]
                            supportActionBar?.title = data.name

                            with(binding) {

                                progressBar.visibility = View.GONE
                                loadingCover.visibility = View.GONE
                                fab.visibility = View.VISIBLE

                                Glide.with(this@DetailActivity)
                                    .load(data.image)
                                    .into(imgThumbnailDetail)

                                tvName.text = data.name
                                tvRelease.text = data.release
                                tvStorage.text = data.storage
                                tvRam.text = data.ram
                                tvDimension.text = data.dimension
                                tvDisplay.text = data.display
                                tvBattery.text = data.battery
                                tvSelfieCamera.text = data.selfieCamera
                                tvMainCamera.text = data.mainCamera
                                tvOs.text = data.os
                                tvChipset.text = data.chipset
                                tvCpu.text = data.cpu
                                tvGpu.text = data.gpu

                                var statusFavorite = iphone.isFavorite
                                setStatusFavorite(statusFavorite)

                                binding.fab.setOnClickListener {
                                    statusFavorite = !statusFavorite
                                    viewModel.setFavorite(iphone, statusFavorite)
                                    setStatusFavorite(statusFavorite)
                                }
                            }
                        }
                        is Resource.Error -> {
                            with(binding) {
                                loadingCover.visibility = View.GONE
                                progressBar.visibility = View.GONE
                                viewError.root.visibility = View.VISIBLE
                                viewError.tvError.text =
                                    detail.message ?: getString(R.string.something_wrong)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite_white
                )
            )
        } else {
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_not_favorite_white
                )
            )
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}