package com.example.meteoParis.Main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.meteoParis.Model.Forecast
import com.example.meteoParis.R
import com.example.meteoParis.Utils.WtoI


abstract class ForcastAdapter(val context: Context, val forecastList: List<Forecast>?) : RecyclerView.Adapter<ForcastAdapter.ViewHolder>() {

    /*
    * Lambda function used as a callback
    * to listen to click events when any forecast item
    * is clicked
    * */
    private var mListener: (forecast: Forecast) -> Unit = {}

    override fun getItemCount() = forecastList?.size ?: 0

     fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bindData(position)
    }

     fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recyclerview_forcast, parent, false)
        return ViewHolder(view)
    }

    inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!), View.OnClickListener {

        init {
            itemView?.setOnClickListener(this)
        }

        val dayTextView = itemView?.findViewById(R.id.day_text_view) as TextView
        val weatherImageView = itemView?.findViewById(R.id.weather_image_view) as ImageView
        val temperatureTextView = itemView?.findViewById(R.id.temperature_text_view) as TextView

        fun bindData(position: Int) {
            val forecast = forecastList?.get(position)

            dayTextView.text = forecast?.day
            val high = forecast?.high?.toInt() ?: 0
            val low = forecast?.low?.toInt() ?: 0
            val formattedTemperatureText = String.format(context.getString(R.string.celcuis_temperature), ((high + low) / 2).toString())
            temperatureTextView.text = formattedTemperatureText

            weatherImageView.setImageResource(WtoI.getImageForCode(forecast?.code ?: "3200"))
        }

        override fun onClick(v: View?) {
            mListener(forecastList?.get(adapterPosition)!!)
        }
    }

    fun addActionListener(listener: (forecast: Forecast) -> Unit) {
        mListener = listener
    }
}