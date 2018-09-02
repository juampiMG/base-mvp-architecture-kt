package com.jp.data.entity.sample

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AssetsEntity {

    @SerializedName("logo")
    @Expose
    var logo: LogoEntity? = null
    @SerializedName("cover-tiny")
    @Expose
    var coverTiny: CoverTinyEntity? = null
    @SerializedName("cover-small")
    @Expose
    var coverSmall: CoverSmallEntity? = null
    @SerializedName("cover-medium")
    @Expose
    var coverMedium: CoverMediumEntity? = null
    @SerializedName("cover-large")
    @Expose
    var coverLarge: CoverLargeEntity? = null
    @SerializedName("icon")
    @Expose
    var icon: IconEntity? = null
    @SerializedName("background")
    @Expose
    var background: BackgroundEntity? = null
    @SerializedName("foreground")
    @Expose
    var foreground: Any? = null
}