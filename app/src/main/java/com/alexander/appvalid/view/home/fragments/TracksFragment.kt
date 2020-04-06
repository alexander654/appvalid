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
import com.alexander.appvalid.adapters.AdapterTracks
import com.alexander.appvalid.databinding.FragmentTracksBinding
import com.alexander.appvalid.view.home.viewmodel.MainViewModel
import org.koin.android.ext.android.inject

class TracksFragment : Fragment() {

    private val adapterTracks by inject<AdapterTracks>()
    private val viewModel by activityViewModels<MainViewModel>()
    private lateinit var binding: FragmentTracksBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tracks, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViewModel()
        initializeRecyclerView()
    }

    private fun initializeRecyclerView() {
        binding.rvTracks.run {
            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
            val manager =
                LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            layoutManager = manager
            adapter = this@TracksFragment.adapterTracks
        }
    }

    private fun initializeViewModel() {
        viewModel.getTracks()
        viewModel.tracksResult.observe(requireActivity(), Observer { data ->
            binding.empty = data.isEmpty() == true
            if (data.isNotEmpty())
                data?.let { adapterTracks.submitList(it) }
        })
    }
}
