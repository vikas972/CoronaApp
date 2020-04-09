package com.vikasmaurya.corona.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


    class Datum {
        @SerializedName("country")
        @Expose
        var country: String? = null
        @SerializedName("countryInfo")
        @Expose
        var countryInfo: CountryInfo? = null
        @SerializedName("cases")
        @Expose
        var cases: Int? = null
        @SerializedName("todayCases")
        @Expose
        var todayCases: Int? = null
        @SerializedName("deaths")
        @Expose
        var deaths: Int? = null
        @SerializedName("todayDeaths")
        @Expose
        var todayDeaths: Int? = null
        @SerializedName("recovered")
        @Expose
        var recovered: Int? = null
        @SerializedName("active")
        @Expose
        var active: Int? = null
        @SerializedName("critical")
        @Expose
        var critical: Int? = null
        @SerializedName("casesPerOneMillion")
        @Expose
        var casesPerOneMillion: Double? = null
        @SerializedName("deathsPerOneMillion")
        @Expose
        var deathsPerOneMillion: Double? = null

       inner class CountryInfo {

           @SerializedName("_id")
           @Expose
           var id: Int? = null
           @SerializedName("country")
           @Expose
           var country: String? = null
           @SerializedName("iso2")
           @Expose
           var iso2: String? = null
           @SerializedName("iso3")
           @Expose
           var iso3: String? = null
           @SerializedName("lat")
           @Expose
           var lat: Double? = null
           @SerializedName("long")
           @Expose
           var long: Double? = null
           @SerializedName("flag")
           @Expose
           var flag: String? = null
       }
    }

