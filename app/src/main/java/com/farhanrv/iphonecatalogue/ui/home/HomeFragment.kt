package com.farhanrv.iphonecatalogue.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.farhanrv.iphonecatalogue.R
import com.farhanrv.iphonecatalogue.core.data.Resource
import com.farhanrv.iphonecatalogue.core.ui.IphoneAdapter
import com.farhanrv.iphonecatalogue.databinding.FragmentHomeBinding
import com.farhanrv.iphonecatalogue.ui.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModel()
    private val binding: FragmentHomeBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val homeAdapter = IphoneAdapter()
            homeAdapter.onItemClick = { selectedData ->
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            viewModel.iphone.observe(viewLifecycleOwner) { iphone ->
                if (iphone != null) {
                    when (iphone) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            homeAdapter.setData(iphone.data)
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text =
                                iphone.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            }

            with(binding.rvIphone) {
                layoutManager = GridLayoutManager(context, 3)
                setHasFixedSize(true)
                adapter = homeAdapter
            }
        }
    }
}