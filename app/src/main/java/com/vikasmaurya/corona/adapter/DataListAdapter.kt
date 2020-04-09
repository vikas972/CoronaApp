package com.vikasmaurya.corona.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vikasmaurya.corona.R
import com.vikasmaurya.corona.model.Datum
import com.squareup.picasso.Picasso

class DataListAdapter(private val context: Context, private val dataList: List<Datum>) : RecyclerView.Adapter<DataListAdapter.RecyclerViewAdapter>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): RecyclerViewAdapter {

        val view = LayoutInflater.from(context).inflate(R.layout.custom_country_data, viewGroup, false)
        return RecyclerViewAdapter(view)

    }


    override fun onBindViewHolder(holder: RecyclerViewAdapter, pos: Int) {

        val list_data = dataList

        Picasso.get()
            .load("${list_data[pos].countryInfo!!.flag}")
            .into(holder.country_image)

        holder.country_name.text = "${list_data[pos].country}"
        holder.country_total_cases.text = "${list_data[pos].cases}"
        holder.country_total_deaths.text = "${list_data[pos].deaths}"
        holder.country_total_recovered.text = "${list_data[pos].recovered}"


    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class RecyclerViewAdapter(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var country_image: ImageView
        var country_name : TextView
        var country_total_recovered : TextView
        var country_total_cases : TextView
        var country_total_deaths : TextView

        init {

            country_image = itemView.findViewById(R.id.country_img)
            country_name = itemView.findViewById(R.id.country_name)
            country_total_recovered = itemView.findViewById(R.id.total_recovered)
            country_total_deaths = itemView.findViewById(R.id.total_deaths)
            country_total_cases = itemView.findViewById(R.id.total_cases)

        }



    }

}