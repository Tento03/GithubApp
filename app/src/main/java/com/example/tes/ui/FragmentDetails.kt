package com.example.tes.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.tes.R
import com.example.tes.databinding.FragmentDetailsBinding
import com.example.tes.github.GithubViewModel
import com.example.tes.room.FavoriteViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class FragmentDetails : Fragment(R.layout.fragment_details) {
    private lateinit var binding: FragmentDetailsBinding
    private val viewModel by viewModels<GithubViewModel>()
    private val favoriteViewModel by viewModels<FavoriteViewModel>()
    private val args by navArgs<FragmentDetailsArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailsBinding.bind(view)

        val progressBar=binding.progressBar
        progressBar.visibility=View.GONE
        viewModel.detailUser(args.user.login).observe(viewLifecycleOwner) { user ->
            Glide.with(requireContext())
                .load(user.avatar_url)
                .into(binding.ivProfile)
            binding.tvName.text = user.login
            binding.tvFollowers.text = user.followers
            binding.tvFollowing.text = user.following
            progressBar.visibility=View.GONE
        }

        binding.tvFollowers.setOnClickListener() {
            val action = FragmentDetailsDirections.actionNavigationDetailsToNavigationFollowers(args.user)
            findNavController().navigate(action)
        }

        binding.tvFollowing.setOnClickListener() {
            val action = FragmentDetailsDirections.actionNavigationDetailsToNavigationFollowings(args.user)
            findNavController().navigate(action)
        }

        var isChecked = false
        CoroutineScope(Dispatchers.IO).launch {
            val count = favoriteViewModel.getFavoriteId(args.user.id)
            withContext(Dispatchers.Main) {
                if (count == null) {
                    isChecked = false
                    binding.toggleFavorite.isChecked = false
                } else {
                    isChecked = true
                    binding.toggleFavorite.isChecked = true
                }
            }
        }

        binding.toggleFavorite.setOnClickListener() {
            if (!isChecked) {
                binding.toggleFavorite.isChecked = true
                favoriteViewModel.addtoFavorite(args.user)
            } else {
                binding.toggleFavorite.isChecked = false
                favoriteViewModel.deleteFavorite(args.user.id)
            }
        }

        val viewPager = binding.viewPager
        val adapter = ViewPagerAdapter(requireActivity(), args.user)
        viewPager.adapter = adapter
        val tabLayout = binding.tabs
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            if (position == 0) {
                tab.text = "Followers"
            } else if (position == 1) {
                tab.text = "Following"
            }
        }.attach()
    }
}
