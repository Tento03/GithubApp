package com.example.tes.room

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import org.jetbrains.annotations.NotNull

@Entity(tableName = "favGH")
@Parcelize
data class Favorite(
    val login:String,
    @PrimaryKey
    @NotNull
    val id:String,val avatar_url:String,
                val followers_url:String,var following_url:String,val name:String,val following:String,var followers:String):
    Parcelable {
    constructor():this("","","","","","","","")
}