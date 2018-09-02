package com.jp.data.entity.sample

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResultSampleEntity {
    @SerializedName("data")
    @Expose
    var data: List<SampleEntity>? = null
}