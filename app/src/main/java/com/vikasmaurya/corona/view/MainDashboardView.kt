package com.vikasmaurya.corona.view

import com.vikasmaurya.corona.model.Datum
import com.vikasmaurya.corona.model.TotalData

interface MainDashboardView {
    fun showLoading()
    fun hideLoading()
    fun getResult(datumModel: List<Datum>)
    fun getAllResult(listTotal: TotalData)
    fun showProgress()
    fun hideProgress()
    fun onRequestSuccess(message: String)
    fun onRequestError(message: String)
}