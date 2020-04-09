package com.vikasmaurya.corona.activity


import android.app.ProgressDialog
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.airbnb.lottie.LottieAnimationView
import com.chivorn.smartmaterialspinner.SmartMaterialSpinner
import com.vikasmaurya.corona.R
import com.vikasmaurya.corona.model.Datum
import com.vikasmaurya.corona.model.TotalData
import com.vikasmaurya.corona.presenter.MainDashboardPresenter
import com.vikasmaurya.corona.view.MainDashboardView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_country.*


class CountryFragment : Fragment(), MainDashboardView
{

    lateinit var mTotalCases : TextView
    lateinit var mTotalDeaths : TextView
    lateinit var mTotalDeathsCountry : TextView
    lateinit var mTotalRecovered : TextView
    lateinit var mTotalRecoveredCountry : TextView
    lateinit var mActiveCases : TextView
    lateinit var mCriticalCases : TextView
    lateinit var mTodayDeaths : TextView
    lateinit var mCountryName : TextView
    lateinit var mTodayCases : TextView
    lateinit var mCountryImage : ImageView
    lateinit var mMainDashboardPresenter : MainDashboardPresenter
    lateinit var mDataListModel : List<Datum>
    lateinit var mProgressDialog : ProgressDialog
    lateinit var mVirus: LottieAnimationView
    lateinit var spProvince: SmartMaterialSpinner<String>
     var provinceList: MutableList<String>? = null
     var totalCasesList: MutableList<String>? = null
     var totalDeathsList: MutableList<String>? = null
     var totalrecoveredList: MutableList<String>? = null
     var totalActiveList: MutableList<String>? = null
     var totalCriticalList: MutableList<String>? = null
     var totaltodaysDeathsList: MutableList<String>? = null
     var todaycases: MutableList<String>? = null
     var countryImage: MutableList<String>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_country, container, false)
        mProgressDialog = ProgressDialog(activity)
        mProgressDialog.setMessage("Wait..")
        mTotalCases = v.findViewById(R.id.totalCases)
        mTotalDeaths = v.findViewById(R.id.totalDeaths)
        mTotalRecoveredCountry = v.findViewById(R.id.total_recovered)
        mTotalRecovered = v.findViewById(R.id.totalRecovered)
        mTotalDeathsCountry = v.findViewById(R.id.total_deaths)
        mActiveCases = v.findViewById(R.id.active_cases)
        mCriticalCases = v.findViewById(R.id.critical_cases)
        mTodayDeaths = v.findViewById(R.id.total_today_deaths)
        mCountryName = v.findViewById(R.id.country_name)
        mTodayCases = v.findViewById(R.id.today_cases)
        mCountryImage = v.findViewById(R.id.country_img)
        mMainDashboardPresenter = MainDashboardPresenter(this)
        mVirus = v.findViewById(R.id.virus_lottie)
        spProvince = v.findViewById(R.id.spinner1) as SmartMaterialSpinner<String>

        initSpinner()

        mMainDashboardPresenter.getData()
        mMainDashboardPresenter.getAllData()

        mVirus.setOnClickListener {

            showMessageCorona("DO THE FIVE TO STOP ME","1.HANDS Wash them often\n\n2.ELBOW Cough into it\n\n3.FACE Don't touch it\n\n4.SPACE Keep safe distance\n\n5.Home Stay if you can")

        }


        return v
    }

    private fun initSpinner() {

        provinceList = ArrayList<String>()
        totalCasesList = ArrayList<String>()
        totalDeathsList = ArrayList<String>()
        totalrecoveredList = ArrayList<String>()
        totalActiveList = ArrayList<String>()
        totalCriticalList = ArrayList<String>()
        totaltodaysDeathsList = ArrayList<String>()
        todaycases = ArrayList<String>()
        countryImage = ArrayList<String>()


    }
    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun getResult(datumModel: List<Datum>) {

        mDataListModel = datumModel

        for (element in datumModel)
        {
            provinceList!!.add(element.country.toString())
            totalCasesList!!.add(element.cases.toString())
            totalDeathsList!!.add(element.deaths.toString())
            totalrecoveredList!!.add(element.recovered.toString())
            totalActiveList!!.add(element.active.toString())
            totalCriticalList!!.add(element.critical.toString())
            totaltodaysDeathsList!!.add(element.todayDeaths.toString())
            todaycases!!.add(element.todayCases.toString())
            countryImage!!.add(element.countryInfo!!.flag.toString())

            spProvince!!.item = provinceList!!


            spProvince!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adapterView: AdapterView<*>, view: View, position: Int, id: Long) {
                    ll.visibility = View.VISIBLE
                    mVirus.visibility = View.GONE
                    Picasso.get()
                        .load("${countryImage!![position]}")
                        .into(mCountryImage)
                    mCountryName.text = provinceList!![position]
                    mTotalRecoveredCountry.text = totalCasesList!![position]
                    mTodayCases.text = todaycases!![position]
                    mTotalRecoveredCountry.text = totalrecoveredList!![position]
                    mTotalDeathsCountry.text = totalDeathsList!![position]
                    mCriticalCases.text = totalCriticalList!![position]
                    mActiveCases.text = totalActiveList!![position]
                    mTodayDeaths.text = totaltodaysDeathsList!![position]

                }

                override fun onNothingSelected(adapterView: AdapterView<*>) {
                    Toast.makeText(activity,"Select Country to Search", Toast.LENGTH_SHORT).show()

                }
            }
        }

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

    private fun showMessageCorona(title: String, message: String) {
        val builder = androidx.appcompat.app.AlertDialog.Builder(activity!!)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("PLEDGE") { dialog, which -> dialog.dismiss()}
        builder.setCancelable(false)
        builder.show()
    }


}
