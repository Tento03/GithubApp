package com.example.tes.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tes.R
import com.example.tes.api.User
import com.example.tes.databinding.FragmentFollowersBinding
import com.example.tes.follow.FollowAdapter
import com.example.tes.follow.FollowViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentFollowers:Fragment(R.layout.fragment_followers) {
    lateinit var binding: FragmentFollowersBinding
    lateinit var followAdapter: FollowAdapter
    private var userList= arrayListOf<User>()
    private val viewModel by viewModels<FollowViewModel>()
    private val args by navArgs<FragmentDetailsArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentFollowersBinding.bind(view)

        var progressBar=binding.progressBar
        binding.progressBar.visibility=View.VISIBLE
        followAdapter= FollowAdapter(requireContext(),userList)
        binding.rv.apply {
            layoutManager=LinearLayoutManager(requireContext())
            adapter=followAdapter
        }
        viewModel.getFollowers(args.user.login).observe(viewLifecycleOwner){
            followAdapter.setList(it)
            followAdapter.notifyDataSetChanged()
            progressBar.visibility=View.GONE
        }
    }

    companion object {
        fun newInstance(user: User): FragmentFollowers {
            val fragment = FragmentFollowers()
            val args = Bundle()
            args.putParcelable("user", user)
            fragment.arguments = args
            return fragment
        }
    }

}