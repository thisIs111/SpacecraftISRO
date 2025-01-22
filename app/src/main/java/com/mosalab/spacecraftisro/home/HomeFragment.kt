package com.mosalab.spacecraftisro.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mosalab.spacecraftisro.R
import com.mosalab.spacecraftisro.core.data.Resource
import com.mosalab.spacecraftisro.core.ui.SpacecraftAdapter
import com.mosalab.spacecraftisro.databinding.FragmentHomeBinding
import com.mosalab.spacecraftisro.detail.DetailSpacecraftActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment(){
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        if(activity != null){
            val spacecraftAdapter = SpacecraftAdapter()
            spacecraftAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailSpacecraftActivity::class.java)
                intent.putExtra(DetailSpacecraftActivity.DATA_KEY, selectedData)
                startActivity(intent)
            }

            homeViewModel.spacecraft.observe(viewLifecycleOwner){ spacecraft ->
                if(spacecraft != null){
                    when(spacecraft){
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success ->{
                            binding.progressBar.visibility = View.GONE
                            spacecraftAdapter.submitList(spacecraft.data)
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text =
                                spacecraft.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            }

            with(binding.rvSpacecraft){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = spacecraftAdapter
            }

        }
    }

}