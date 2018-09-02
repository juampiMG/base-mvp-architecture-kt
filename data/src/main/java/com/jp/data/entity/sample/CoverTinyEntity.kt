package com.jp.data.entity.sample

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CoverTinyEntity {

    @SerializedName("uri")
    @Expose
    var uri: String? = null
    @SerializedName("width")
    @Expose
    var width: Int = 0
    @SerializedName("height")
    @Expose
    var height: Int = 0
}