package com.example.sinemo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

open class ImageChangeBroadcastReceiver: BroadcastReceiver() {
    var startrec = false
    override fun onReceive(p0: Context?, p1: Intent?) {
        val receivedNotificationCode: Int = p1!!.getIntExtra("Notification Code", -1)
        Log.d("RNC", receivedNotificationCode.toString())
        changeInterceptedNotificationImage(receivedNotificationCode)
    }
    private fun changeInterceptedNotificationImage(notificationCode: Int) {
        when (notificationCode) {
            NotificationListener.InterceptedNotificationCode.INSTAGRAM_CODE -> startrec = true

            NotificationListener.InterceptedNotificationCode.OTHER_NOTIFICATIONS_CODE -> startrec = false
        }
        Log.d("CHECK", startrec.toString())
    }
}