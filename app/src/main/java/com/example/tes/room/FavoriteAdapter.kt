package com.example.tes.room

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.tes.R
import com.example.tes.databinding.ItemUserBinding


class FavoriteAdapter(private val dataSet: ArrayList<Favorite>,var listener:OnItemClickBack) :
    RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    inner class FavoriteViewHolder(var binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(favoriteMovie: Favorite){
            binding.apply {
                Glide.with(itemView)
                    .load(favoriteMovie.avatar_url)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error)
                    .into(ivUser)
                tvUsername.text=favoriteMovie.login
                binding.root.setOnClickListener(){
                    listener.onItemClick(favoriteMovie)
                }
            }
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): FavoriteViewHolder {
        // Create a new view, which defines the UI of the list item
        var binding=ItemUserBinding.inflate(LayoutInflater.from(viewGroup.context),viewGroup,false)
        return FavoriteViewHolder(binding)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: FavoriteViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
//        viewHolder.textView.text = dataSet[position]
        val repoItem=dataSet[position]
        if (repoItem!=null){
            viewHolder.bind(repoItem)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size
    interface OnItemClickBack{
        fun onItemClick(favorite: Favorite)
    }
    fun setMovieList(newFav:List<Favorite>){
        dataSet.addAll(newFav)
        notifyDataSetChanged()
    }

}
