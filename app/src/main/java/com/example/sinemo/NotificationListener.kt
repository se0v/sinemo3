package com.example.sinemo

import android.content.Intent
import android.os.IBinder
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification

class NotificationListener: NotificationListenerService() {
    private object ApplicationPackageNames {
        const val INSTAGRAM_PACK_NAME = "com.instagram.android"
    }
    object InterceptedNotificationCode {
        const val INSTAGRAM_CODE = 1
        const val OTHER_NOTIFICATIONS_CODE = 2 // ignore all notification
    }
    override fun onBind(intent: Intent?): IBinder? {
        return super.onBind(intent)
    }
    override fun onNotificationRemoved(sbn: StatusBarNotification?) {

        val notificationCode: Int = matchNotificationCode(sbn!!)

        if (notificationCode != InterceptedNotificationCode.OTHER_NOTIFICATIONS_CODE) {
            val intent = Intent("com.example.sinemo")
            intent.putExtra("Notification Code", notificationCode)
            sendBroadcast(intent)
        }
    }
    private fun matchNotificationCode(sbn: StatusBarNotification): Int {
        val packageName = sbn.packageName
        return if (packageName == ApplicationPackageNames.INSTAGRAM_PACK_NAME) {
            InterceptedNotificationCode.INSTAGRAM_CODE
        } else {
            InterceptedNotificationCode.OTHER_NOTIFICATIONS_CODE
        }
    }
}