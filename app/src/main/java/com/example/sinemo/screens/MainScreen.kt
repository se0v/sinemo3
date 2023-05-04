package com.example.sinemo.screens

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
import com.example.sinemo.req
import com.example.sinemo.stopRecording
import com.example.sinemo.telepass
import com.example.sinemo.MainActivity
import com.example.sinemo.tgPassportResult
import org.telegram.passport.TelegramPassport

@Composable
fun MainScreen(){

    Column(
        modifier = Modifier
            .background((Color.DarkGray))
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Button(onClick = {
            stopRecording()
            val mainActivity = MainActivity
            mainActivity.sendFile()
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
            mainActivity.telep()
        }, colors = ButtonDefaults
            .buttonColors(backgroundColor = Color.Cyan)) {
            Text(text = "Log in telegram", color = Color.Black)
        }
    }
}


}