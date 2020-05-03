package com.test.lifehack.model

import com.google.gson.annotations.SerializedName
import com.test.lifehack.App
import java.io.Serializable

data class CompanyModel(
    var id: Int,
    var name: String,
    @SerializedName("img")
    var imageUrl: String
) : Serializable {
    fun getImage(): String {
        return "${App.PHOTO_HOST}/$imageUrl"
    }
}
