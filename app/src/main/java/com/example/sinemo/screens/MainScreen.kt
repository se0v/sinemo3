package com.example.sinemo.screens

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.FileProvider
import com.example.sinemo.*
import org.telegram.passport.TelegramPassport
import java.io.File
import java.lang.Exception

@Composable
fun MainScreen() {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .background((Color.LightGray))
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Button(onClick = { stopRecording(audioViewModel = AudioViewModel())
            try {
                val file = File(output)
                if(file.exists()) {
                    val uri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".provider", file)
                    val intent = Intent(Intent.ACTION_SEND)
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    intent.type = "audio/ogg"
                    intent.putExtra(Intent.EXTRA_STREAM, uri)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    intent.setPackage("org.telegram.messenger")
                    context.startActivity(intent)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        },
            colors = ButtonDefaults
                .buttonColors(backgroundColor = Color.Black)) {
            Text(text = "Share emotion ")
            Icon(
                imageVector = Icons.Default.Send,
                contentDescription = null
            )
        }
        Button(onClick = {
            telepass()
            TelegramPassport.request(context as Activity?, req, tgPassportResult)
        }, colors = ButtonDefaults
            .buttonColors(backgroundColor = Color.Cyan)) {
            Text(text = "Log in telegram", color = Color.Black)
        }
    }

}