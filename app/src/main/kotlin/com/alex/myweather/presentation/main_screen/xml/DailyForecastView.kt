package com.alex.myweather.presentation.main_screen.xml

import android.view.LayoutInflater
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
            val recyclerView = LayoutInflater
                .from(context)
                .inflate(R.layout.recycler_view, null) as RecyclerView

            recyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = DailyForecastAdapter(data)
            }

            recyclerView
        },
        update = { view ->
            (view.adapter as? DailyForecastAdapter)?.update(data)
        },
        onReset = {view ->
            (view.adapter as? DailyForecastAdapter)?.clear()
        }
    )
}
