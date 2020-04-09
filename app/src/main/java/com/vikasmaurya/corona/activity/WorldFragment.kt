package com.vikasmaurya.corona.activity


import android.app.ProgressDialog
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

import com.vikasmaurya.corona.R
import com.vikasmaurya.corona.adapter.DataListAdapter
import com.vikasmaurya.corona.model.Datum
import com.vikasmaurya.corona.model.TotalData
import com.vikasmaurya.corona.presenter.MainDashboardPresenter
import com.vikasmaurya.corona.view.MainDashboardView

/**
 * A simple [Fragment] subclass.
 */
class WorldFragment : Fragment(),MainDashboardView {

    lateinit var mTotalCases : TextView
    lateinit var mTotalDeaths : TextView
    lateinit var mTotalRecovered : TextView
    lateinit var mMainDashboardPresenter : MainDashboardPresenter
    lateinit var mSwipeRefreshLayout: SwipeRefreshLayout
    lateinit var mDataAdapter : DataListAdapter
    lateinit var mDataListModel : List<Datum>
    lateinit var mProgressDialog : ProgressDialog
    lateinit var mRecyclerView : RecyclerView
    var mTotal : Int? = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v= inflater.inflate(R.layout.fragment_world, container, false)

        mProgressDialog = ProgressDialog(activity)
        mProgressDialog.setMessage("Wait..")
        mRecyclerView = v.findViewById(R.id.recycler_view)
        mTotalCases = v.findViewById(R.id.totalCases)
        mTotalDeaths = v.findViewById(R.id.totalDeaths)
        mTotalRecovered = v.findViewById(R.id.totalRecovered)
        mSwipeRefreshLayout = v.findViewById(R.id.swipe_refresh)
        mMainDashboardPresenter = MainDashboardPresenter(this)

        mRecyclerView.layoutManager = LinearLayoutManager(activity)


        mSwipeRefreshLayout.setOnRefreshListener {
            mMainDashboardPresenter.getData()
            mMainDashboardPresenter.getAllData()

        }


        return v
    }

    override fun showLoading() {
        mSwipeRefreshLayout.isRefreshing = true
    }

    override fun hideLoading() {
        mSwipeRefreshLayout.isRefreshing = false

    }

    override fun getResult(datumModel: List<Datum>) {
        mDataAdapter = DataListAdapter(activity!!,datumModel)
        mDataAdapter.notifyDataSetChanged()
        mRecyclerView.adapter = mDataAdapter
        mDataListModel = datumModel


    }

    override fun getAllResult(listTotal: TotalData) {
        mTotalCases.text = listTotal.cases.toString()
        mTotalRecovered.text = listTotal.recovered.toString()
        mTotalDeaths.text = listTotal.deaths.toString()

    }

    override fun showProgress() {
        mProgressDialog.show()

    }

    override fun hideProgress() {
        mProgressDialog.dismiss()
    }

    override fun onRequestSuccess(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun onRequestError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    private fun checkConnection(): Boolean {
        val cm = activity!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null

    }

    override fun onResume() {
        super.onResume()

        mMainDashboardPresenter.getData()
        mMainDashboardPresenter.getAllData()

        if(!checkConnection())
        {
            showMessage("Connection Problem!!..","May be you are not connected to internet , Try connecting on internet and try again.. ")
        }
    }

    private fun showMessage(title: String, message: String) {
        val builder = androidx.appcompat.app.AlertDialog.Builder(activity!!)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("OK") { dialog, which -> dialog.dismiss()}
        builder.setCancelable(false)
        builder.show()
    }

}
