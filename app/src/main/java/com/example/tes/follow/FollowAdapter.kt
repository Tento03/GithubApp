package com.example.tes.follow

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.tes.api.User
import com.example.tes.databinding.ItemUserBinding

class FollowAdapter(private var context: Context,private val dataSet: ArrayList<User>) :
    RecyclerView.Adapter<FollowAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
   inner class ViewHolder(var binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            Glide.with(context)
                .load(user.avatar_url)
                .transition(DrawableTransitionOptions.withCrossFade())
                .centerCrop()
                .into(binding.ivUser)
            binding.tvUsername.text = user.login
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val binding=ItemUserBinding.inflate(LayoutInflater.from(viewGroup.context),viewGroup,false)

        return ViewHolder(binding)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
//        viewHolder.textView.text = dataSet[position]
        var user=dataSet[position]
        viewHolder.bind(user)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size
    fun setList(newList:ArrayList<User>){
        dataSet.addAll(newList)
        notifyDataSetChanged()
    }
}
