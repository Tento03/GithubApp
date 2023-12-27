package com.example.tes.api

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailsUserResponse(val login:String,val id:Int,val avatar_url:String,
                val followers_url:String,var following_url:String,val name:String,val following:String,var followers:String):
    Parcelable {
}