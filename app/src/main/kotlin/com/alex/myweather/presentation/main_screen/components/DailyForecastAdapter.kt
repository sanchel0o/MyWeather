package com.alex.myweather.presentation.main_screen.components

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.alex.myweather.R
import com.alex.myweather.domain.model.DailyWeatherData

class DailyForecastAdapter(private val dailyWeatherData: List<DailyWeatherData>) :
    RecyclerView.Adapter<DailyForecastAdapter.ViewHolder>()
{

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val dayName: TextView
        val humidityValue: TextView
        val currentWeatherIcon: ImageView
        val mixMaxTemp: TextView

        init {
            dayName = view.findViewById(R.id.dayName)
            humidityValue = view.findViewById(R.id.humidityValue)
            currentWeatherIcon = view.findViewById(R.id.currentWeatherIcon)
            mixMaxTemp = view.findViewById(R.id.mixMaxTemp)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.single_item_layout, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = dailyWeatherData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            dayName.text = dailyWeatherData[position].day
            humidityValue.text = dailyWeatherData[position].humidity.toString()
            currentWeatherIcon.load(dailyWeatherData[position].imageUrl)
            mixMaxTemp.text = dailyWeatherData[position].maxTemp.toString()
        }
    }

}