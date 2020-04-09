package com.vikasmaurya.corona.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TotalData {
    @SerializedName("cases")
    @Expose
    var cases: Int? = null
    @SerializedName("deaths")
    @Expose
    var deaths: Int? = null
    @SerializedName("recovered")
    @Expose
    var recovered: Int? = null
}