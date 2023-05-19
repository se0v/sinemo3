package com.example.sinemo
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi

open class ImageChangeBroadcastReceiver: BroadcastReceiver() {
    private var startRec = false
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onReceive(p0: Context?, p1: Intent?) {
        val receivedNotificationCode: Int = p1!!.getIntExtra("Notification Code", -1)
        Log.d("RNC", receivedNotificationCode.toString())
        changeInterceptedNotificationImage(receivedNotificationCode)
    }
    @RequiresApi(Build.VERSION_CODES.Q)
    private fun changeInterceptedNotificationImage(notificationCode: Int) {
        when (notificationCode) {
            NotificationListener.InterceptedNotificationCode.INSTAGRAM_CODE -> startRecording()
            NotificationListener.InterceptedNotificationCode.OTHER_NOTIFICATIONS_CODE -> startRec = false
        }
        Log.d("CHECK", startRec.toString())
    }
}