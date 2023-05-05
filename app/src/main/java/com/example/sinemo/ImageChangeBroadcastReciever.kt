package com.example.sinemo
import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaRecorder
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.IOException
open class ImageChangeBroadcastReceiver: BroadcastReceiver() {
    private var startrec = false
    lateinit var audioRecordViewModel: AudioRecordViewModel
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onReceive(p0: Context?, p1: Intent?) {
        val receivedNotificationCode: Int = p1!!.getIntExtra("Notification Code", -1)
        Log.d("RNC", receivedNotificationCode.toString())
        changeInterceptedNotificationImage(receivedNotificationCode)
    }
    @RequiresApi(Build.VERSION_CODES.Q)
    private fun changeInterceptedNotificationImage(notificationCode: Int) {
        when (notificationCode) {
            NotificationListener.InterceptedNotificationCode.INSTAGRAM_CODE -> audioRecordViewModel.startRecording()
            NotificationListener.InterceptedNotificationCode.OTHER_NOTIFICATIONS_CODE -> startrec = false
        }
        Log.d("CHECK", startrec.toString())
    }
}