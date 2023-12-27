package com.example.tes.room

import androidx.lifecycle.LiveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoriteRepository @Inject constructor(private var favoriteDao: FavoriteDao){
    suspend fun addtoFavorite(favorite: Favorite){
        return favoriteDao.addtoFavorite(favorite)
    }
    fun getFavorite():LiveData<List<Favorite>>{
       return favoriteDao.getFavorite()
    }
    suspend fun getFavoriteId(id:String):Favorite?{
        return favoriteDao.getFavoriteId(id)
    }
    suspend fun deleteFavorite(id: String):Int{
        return favoriteDao.deleteFavorite(id)
    }
}