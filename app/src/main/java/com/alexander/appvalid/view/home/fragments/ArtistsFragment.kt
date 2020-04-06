package com.alexander.appvalid.view.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexander.appvalid.R
import com.alexander.appvalid.adapters.ArtistsAdapter
import com.alexander.appvalid.databinding.FragmentArtistsBinding
import com.alexander.appvalid.view.home.viewmodel.MainViewModel
import org.koin.android.ext.android.inject

class ArtistsFragment : Fragment() {


    private val adapter by inject<ArtistsAdapter>()
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentArtistsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_artists, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViewModelListener()
        initializeRecyclerView()
    }

    private fun initializeRecyclerView() {
        binding.rvArtists.run {
            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
            val manager =
                LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            layoutManager = manager
            adapter = this@ArtistsFragment.adapter
        }
    }

    private fun initializeViewModelListener() {
        viewModel.getArtists()
        viewModel.artistsResult.observe(requireActivity(), Observer {
            binding.empty = it.isEmpty() == true
            if (it.isNotEmpty())
                adapter.submitList(it)
        })
    }
}
