package com.test.lifehack.model

import com.google.gson.annotations.SerializedName
import com.test.lifehack.App
import java.io.Serializable


data class CompanyInfoModel(
    var id: Int,
    var name: String,
    @SerializedName("img")
    var imageUrl: String,
    var description: String,
    @SerializedName("www")
    var site: String,
    @SerializedName("lat")
    var latitude: Float,
    @SerializedName("lon")
    var longitude: Float,
    @SerializedName("phone")
    var numberPhone:String
): Serializable {
    fun getImage(): String {
        return "${App.PHOTO_HOST}/$imageUrl"
    }
}