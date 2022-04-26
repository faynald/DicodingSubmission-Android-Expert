package com.farhanrv.iphonecatalogue.favorite.ui

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.farhanrv.iphonecatalogue.core.ui.IphoneAdapter
import com.farhanrv.iphonecatalogue.databinding.FragmentHomeBinding
import com.farhanrv.iphonecatalogue.favorite.R
import com.farhanrv.iphonecatalogue.favorite.di.favoriteModule
import com.farhanrv.iphonecatalogue.ui.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: FavoriteViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favoriteModule)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val iphoneAdapter = IphoneAdapter()
        iphoneAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        viewModel.favorite.observe(this) { iphone ->
            if (iphone != null) {
                iphoneAdapter.setData(iphone)
                if (iphoneAdapter.itemCount == 0) {
                    binding.viewError.root.visibility = View.VISIBLE
                    binding.viewError.tvError.text = getString(R.string.empty_error)
                }
            }
        }

        with(binding.rvIphone) {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = iphoneAdapter
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}