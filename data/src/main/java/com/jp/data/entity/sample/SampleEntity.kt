package com.jp.data.entity.sample

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SampleEntity {
    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("names")
    @Expose
    var names: NamesEntity? = null
    @SerializedName("assets")
    @Expose
    var assets: AssetsEntity? = null
}