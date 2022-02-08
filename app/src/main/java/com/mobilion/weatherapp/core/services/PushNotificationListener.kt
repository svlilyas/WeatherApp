package com.mobilion.weatherapp.core.services

import com.google.firebase.messaging.RemoteMessage

interface PushNotificationListener {
    fun notificationReceived(data: RemoteMessage?)
    fun showNotificationMessage(data: RemoteMessage?)
}