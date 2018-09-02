package com.jp.data.entity.sample

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class NamesEntity {

    @SerializedName("international")
    @Expose
    var international: String? = null
    @SerializedName("japanese")
    @Expose
    var japanese: Any? = null
    @SerializedName("twitch")
    @Expose
    var twitch: String? = null
}