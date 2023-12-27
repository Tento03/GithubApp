package com.example.tes.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoriteDao {
    @Insert
    fun addtoFavorite(favorite: Favorite)

    @Query("SELECT * FROM favGH")
    fun getFavorite():LiveData<List<Favorite>>

    @Query("SELECT * FROM favGH where id=:id")
    fun getFavoriteId(id:String):Favorite?

    @Query("DELETE FROM favGH where id=:id")
    fun deleteFavorite(id: String):Int
}