package com.alex.myweather.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.alex.myweather.R
import com.alex.myweather.domain.repository.LocalWeatherRepository
import com.alex.myweather.domain.repository.RemoteWeatherRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val WEATHER_CHANNEL = "WEATHER_CHANNEL"
private const val NOTIFICATION_ID = 1
private const val CITY = "Penza"
private const val DAYS_QUANTITY = 5

@AndroidEntryPoint
class WeatherService @Inject constructor() : Service() {

    @Inject lateinit var remoteRepository: RemoteWeatherRepository
    @Inject lateinit var localWeatherRepository: LocalWeatherRepository

    private val serviceJob = Job()
    private val serviceScope = CoroutineScope(Dispatchers.IO + serviceJob)

    private fun loadWeatherData() {
        serviceScope.launch {
            while (true){
                val result = try {
                    remoteRepository.loadForecastData(city = CITY, days = DAYS_QUANTITY)
                } catch (e: Exception) {
                    Log.d("SERVICE", "Exception message is: ${e.message}")
                    null
                }

                localWeatherRepository.apply {
                    result?.currentWeatherData?.let { saveCurrentWeatherData(it) }
                    result?.dailyWeatherData?.map { saveDailyWeatherData(it) }
                    result?.hourlyWeatherData?.map { saveHourlyWeatherData(it) }
                }
                delay(5000)
            }
        }
    }

    override fun onCreate() {
        super.onCreate()

        createNotificationChannel()

        val notificationBuilder = createNotificationBuilder()
        startForeground(NOTIFICATION_ID, notificationBuilder.build())
        loadWeatherData()
    }

    private fun createNotificationBuilder(): NotificationCompat.Builder =
        NotificationCompat.Builder(this, WEATHER_CHANNEL)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(getString(R.string.notification_title))
            .setContentText(getString(R.string.notification_message))
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setCategory(Notification.CATEGORY_SERVICE)
            .setSilent(true)
            .setStyle(
                NotificationCompat.InboxStyle()
                    .addLine("Catching data")
            )
            .setOngoing(true)

    private fun createNotificationChannel() {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (notificationManager.getNotificationChannel(WEATHER_CHANNEL) != null) return

        val channel = NotificationChannel(
            WEATHER_CHANNEL,
            getString(R.string.channel_name),
            NotificationManager.IMPORTANCE_LOW
        )

        notificationManager.createNotificationChannel(channel)
    }

    override fun onBind(p0: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int = START_STICKY

    override fun onDestroy() {
        serviceScope.cancel()
        super.onDestroy()
    }
}
