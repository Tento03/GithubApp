package com.example.tes.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tes.R
import com.example.tes.api.User
import com.example.tes.databinding.FragmentHomeBinding
import com.example.tes.github.GithubAdapter
import com.example.tes.github.GithubViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentHome:Fragment(R.layout.fragment_home) {
    lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModels<GithubViewModel>()
    lateinit var githubAdapter: GithubAdapter
    private var userList= arrayListOf<User>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentHomeBinding.bind(view)

        val progressBar=binding.progressBar
        githubAdapter= GithubAdapter(requireContext(),userList,object :GithubAdapter.onItemClickListener{
            override fun detail(user: User) {
                val user=User(user.login,user.id,user.avatar_url,user.followers_url,user.following_url,user.name,user.following,user.followers)
                var action=FragmentHomeDirections.actionNavigationHomeToNavigationDetails(user)
                findNavController().navigate(action)
            }

        })
        binding.apply {
            rv.layoutManager=LinearLayoutManager(requireContext())
            rv.adapter=githubAdapter
        }
        setHasOptionsMenu(true)
        viewModel.github.observe(viewLifecycleOwner){
            githubAdapter.setList(it.items)
            githubAdapter.notifyDataSetChanged()
            if (it.items.isEmpty()){
                progressBar.visibility=View.VISIBLE
            }
            else{
                progressBar.visibility=View.GONE
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_gallery,menu)
        val searchItem=menu.findItem(R.id.action_search)
        val searchView=searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrBlank()){
                    binding.progressBar.visibility = View.VISIBLE
                    binding.rv.scrollToPosition(0)
                    viewModel.searchUser(query)
                    githubAdapter.notifyDataSetChanged()
                    searchView.clearFocus()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })
    }
}