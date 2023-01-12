package com.example.aziz_musaev_hw_14.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import com.example.aziz_musaev_hw_14.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebaseNotifications:FirebaseMessagingService() {
    override fun onMessageReceived(message: RemoteMessage) {
        Log.e("ololo", "onMessageReceived: "+ message.notification?.title)
        Log.e("ololo", "onMessageReceived: "+ message.notification?.body)
        sendNotification(message)
        super.onMessageReceived(message)
    }



    private fun sendNotification(remoteMessage: RemoteMessage){
val notificationBuilder = NotificationCompat.Builder(this,"task_channelId")
        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher)
        notificationBuilder.setContentTitle(remoteMessage.notification?.title)
        notificationBuilder.setContentText(remoteMessage.notification?.body)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel("task_channelId",
                "Channel human readable title",
                NotificationManager.IMPORTANCE_DEFAULT)
        } else {
            TODO("VERSION.SDK_INT < O")
        }

        notificationManager.createNotificationChannel(channel)
        notificationManager.notify(1,notificationBuilder.build())
    }
}