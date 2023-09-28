package com.alex.myweather.presentation.main_screen.xml

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.alex.myweather.R
import com.alex.myweather.domain.model.DailyWeatherData

class DailyForecastAdapter(dailyWeatherData: List<DailyWeatherData>) :
    RecyclerView.Adapter<DailyForecastAdapter.ViewHolder>()
{

    private val values : MutableList<DailyWeatherData> = dailyWeatherData.toMutableList()

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

    fun update(dailyWeatherData: List<DailyWeatherData>){
        values.clear()
        values.addAll(dailyWeatherData)
        this.notifyDataSetChanged()
    }

    fun clear(){
        values.clear()
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recycler_view, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = values.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            dayName.text = values[position].day
            humidityValue.text = values[position].humidity.toString()
            currentWeatherIcon.load(values[position].imageUrl)
            mixMaxTemp.text = values[position].maxTemp.toString()
        }
    }
}
