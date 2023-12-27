package com.example.tes.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tes.R
import com.example.tes.api.User
import com.example.tes.databinding.FragmentFavoriteBinding
import com.example.tes.room.Favorite
import com.example.tes.room.FavoriteAdapter
import com.example.tes.room.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentFavorite:Fragment(R.layout.fragment_favorite) {
    lateinit var binding: FragmentFavoriteBinding
    private val viewModel by viewModels<FavoriteViewModel>()
    private val args by navArgs<FragmentFavoriteArgs>()
    lateinit var favoriteAdapter: FavoriteAdapter
    private var favList= arrayListOf<Favorite>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentFavoriteBinding.bind(view)

        favoriteAdapter= FavoriteAdapter(favList,object :FavoriteAdapter.OnItemClickBack{
            override fun onItemClick(favorite: Favorite) {
                val user= User(favorite.login,favorite.id,favorite.avatar_url,favorite.followers_url,favorite.following_url,favorite.name,favorite.following,favorite.followers)
                var action=FragmentFavoriteDirections.actionNavigationFavoriteToNavigationDetails(user)
                findNavController().navigate(action)
            }

        })
        binding.apply {
            rvMovie.layoutManager=LinearLayoutManager(requireContext())
            rvMovie.adapter=favoriteAdapter
        }
        viewModel.getFavorite().observe(viewLifecycleOwner){
            favoriteAdapter.setMovieList(it)
            favoriteAdapter.notifyDataSetChanged()
        }
    }
}