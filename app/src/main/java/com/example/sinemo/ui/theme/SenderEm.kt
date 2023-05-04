package com.example.sinemo.ui.theme

import android.content.Intent
import androidx.core.content.FileProvider
import com.example.sinemo.BuildConfig
import com.example.sinemo.output
import java.io.File

fun senderEm(){
    try {
        val file = File(output)
        if(file.exists()) {
            val uri = FileProvider.getUriForFile(this@MainActivity, BuildConfig.APPLICATION_ID + ".provider", file)
            val intent = Intent(Intent.ACTION_SEND)
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            intent.type = "audio/ogg"
            intent.putExtra(Intent.EXTRA_STREAM, uri)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.setPackage("org.telegram.messenger")
        }
    } catch (e: java.lang.Exception) {
        e.printStackTrace()
    }
}