package com.example.tes.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.tes.api.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private var repository: FavoriteRepository):ViewModel() {
    fun addtoFavorite(user: User){
        CoroutineScope(Dispatchers.IO).launch {
            val user=Favorite(user.login,user.id,user.avatar_url,user.followers_url,user.following_url,user.name,user.following,user.followers)
            repository.addtoFavorite(user)
        }
    }
    fun getFavorite():LiveData<List<Favorite>>{
        return repository.getFavorite()
    }
    suspend fun getFavoriteId(id:String):Favorite?{
        return repository.getFavoriteId(id)
    }
    fun deleteFavorite(id:String){
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteFavorite(id)
        }
    }
}