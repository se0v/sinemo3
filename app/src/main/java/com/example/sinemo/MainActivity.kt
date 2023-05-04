package com.example.sinemo
import android.annotation.SuppressLint
import android.app.*
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.content.FileProvider
import androidx.navigation.compose.rememberNavController
import com.example.sinemo.navigation.AppNavigation
import com.example.sinemo.ui.theme.SinemoTheme
import com.example.sinemo.ui.theme.senderEm
import org.telegram.passport.*
import java.io.File
class MainActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition", "NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var imageChangeBroadcastReceiver: ImageChangeBroadcastReceiver? = null
            //register a receiver to tell the MainActivity when a notification has been received
            imageChangeBroadcastReceiver = ImageChangeBroadcastReceiver()
            val intentFilter = IntentFilter()
            intentFilter.addAction("com.example.sinemo")
            registerReceiver(imageChangeBroadcastReceiver, intentFilter)

            val navController = rememberNavController()

            SinemoTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text("SinEmo")
                            },
                            actions =
                            {
                                IconButton(onClick = {})
                                {
                                    Icon(
                                        imageVector = Icons.Default.Face,
                                        contentDescription = null
                                    )

                                }
                            }
                        )
                    },
                    content = {
                        AppNavigation(navController)
                        Column(
                            modifier = Modifier
                                .background((Color.DarkGray))
                                .fillMaxWidth()
                                .fillMaxHeight(),
                            verticalArrangement = Arrangement.SpaceBetween,
                            horizontalAlignment = Alignment.CenterHorizontally
                        )
                        {
                            Button(onClick = { stopRecording()
                                senderEm()
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
                                TelegramPassport.request(this@MainActivity, req, tgPassportResult)
                            }, colors = ButtonDefaults
                                .buttonColors(backgroundColor = Color.Cyan)) {
                                Text(text = "Log in telegram", color = Color.Black)
                            }
                        }
                    }
                )
            }
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable("payload", payload)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 352) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "login_successful", Toast.LENGTH_SHORT).show()
            } else if (resultCode == TelegramPassport.RESULT_ERROR) {
                if (data != null) {
                    AlertDialog.Builder(this)
                        .setTitle("error")
                        .setMessage(data.getStringExtra("error"))
                        .setPositiveButton("ok", null)
                        .show()
                }
            } else {
                Toast.makeText(this, "login_canceled", Toast.LENGTH_SHORT).show()
            }}
}}











