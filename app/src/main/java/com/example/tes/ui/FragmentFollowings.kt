package com.example.tes.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tes.R
import com.example.tes.api.User
import com.example.tes.databinding.FragmentFollowingsBinding
import com.example.tes.follow.FollowAdapter
import com.example.tes.follow.FollowViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentFollowings:Fragment(R.layout.fragment_followings) {
    lateinit var binding:FragmentFollowingsBinding
    private val viewModel by viewModels<FollowViewModel>()
    private val args by navArgs<FragmentFollowingsArgs>()
    lateinit var followAdapter:FollowAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentFollowingsBinding.bind(view)

        val progressBar=binding.progressBar
        progressBar.visibility=View.VISIBLE
        followAdapter= FollowAdapter(requireContext(), arrayListOf())
        binding.rv.layoutManager= LinearLayoutManager(requireContext())
        binding.rv.setHasFixedSize(true)
        binding.rv.adapter=followAdapter

        viewModel.getFollowing(args.user.login).observe(viewLifecycleOwner){
            followAdapter.setList(it)
            progressBar.visibility=View.GONE
        }
    }
    companion object {
        fun newInstance(user: User): FragmentFollowings {
            val fragment = FragmentFollowings()
            val args = Bundle()
            args.putParcelable("user", user)
            fragment.arguments = args
            return fragment
        }
    }
}