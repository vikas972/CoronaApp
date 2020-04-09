package com.vikasmaurya.corona.presenter

import com.appdid.topautocare.api.ApiClient
import com.appdid.topautocare.api.ApiInterface
import com.vikasmaurya.corona.model.Datum
import com.vikasmaurya.corona.model.TotalData
import com.vikasmaurya.corona.view.MainDashboardView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainDashboardPresenter(private val view : MainDashboardView) {

    internal fun getData()
    {
        view.showLoading()

        val apiInterface = ApiClient.apiClient.create(ApiInterface::class.java)

        val call = apiInterface.get_data

        call.enqueue(object : Callback<List<Datum>>
        {
            override fun onResponse(call: Call<List<Datum>>, response: Response<List<Datum>>) {
                view.hideLoading()

                view.getResult(response.body()!!)

            }

            override fun onFailure(call: Call<List<Datum>>, t: Throwable) {
                view.hideLoading()
                view.onRequestError(t.localizedMessage!!)
            }

        })

    }

    internal fun getAllData()
    {
        val apiInterface = ApiClient.apiClient.create(ApiInterface::class.java)

        val call = apiInterface.get_all

        call.enqueue(object : Callback<TotalData>
        {
            override fun onResponse(call: Call<TotalData>, response: Response<TotalData>) {

                view.getAllResult(response.body()!!)

            }

            override fun onFailure(call: Call<TotalData>, t: Throwable) {
                view.onRequestError(t.localizedMessage!!)
            }

        })

    }



}