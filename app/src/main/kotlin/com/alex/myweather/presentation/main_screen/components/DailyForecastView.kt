package com.alex.myweather.presentation.main_screen.components

import android.view.LayoutInflater
import androidx.cardview.widget.CardView
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alex.myweather.R
import com.alex.myweather.domain.model.DailyWeatherData

@Composable
fun DailyForecastView(
    data: List<DailyWeatherData>
) {
    AndroidView(
        modifier = Modifier.fillMaxWidth(),
        factory = { context ->
            LayoutInflater.from(context)
                .inflate(R.layout.card_view, null) as CardView
        },
        update = { view ->
            val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
            val data = data
            val adapter = DailyForecastAdapter(data)

            recyclerView.layoutManager = LinearLayoutManager(view.context)
            recyclerView.adapter = adapter
        }
    )
}