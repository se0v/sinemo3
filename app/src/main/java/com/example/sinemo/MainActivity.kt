package com.example.sinemo

import android.annotation.SuppressLint
import android.app.*
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.View
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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.content.FileProvider
import com.example.sinemo.ui.theme.SinemoTheme
import org.telegram.passport.*
import java.io.File
import java.util.*


class MainActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition", "NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val telegramButton = TelegramLoginButton(this)
            telegramButton.setCornerRoundness(1f)

            var imageChangeBroadcastReceiver: ImageChangeBroadcastReceiver? = null
            // register a receiver to tell the MainActivity when a notification has been received
            imageChangeBroadcastReceiver = ImageChangeBroadcastReceiver()
            val intentFilter = IntentFilter()
            intentFilter.addAction("com.example.sinemo")
            registerReceiver(imageChangeBroadcastReceiver, intentFilter)

            val scaffoldState = rememberScaffoldState()
            val scope = rememberCoroutineScope()
            val runningProcessInfo = mutableListOf<String>()
            val status by remember { mutableStateOf(runningProcessInfo) }
            val appService : ActivityManager = getSystemService(ACTIVITY_SERVICE) as ActivityManager
            val tasks = appService.runningAppProcesses
            for(task in tasks){
                if (task.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    runningProcessInfo.add(task.processName)
                    /*scope.launch { scaffoldState.snackbarHostState.showSnackbar(
                    message = "Bye Nonexistence!")}*/
                }
                Toast.makeText(this.applicationContext, runningProcessInfo.toString(), Toast.LENGTH_LONG).show()
            }

            SinemoTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text("Sinemo")
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
                    content =
                    {
                        Column(
                            modifier = Modifier
                                .background((Color.DarkGray))
                                .fillMaxWidth()
                                .fillMaxHeight(),
                            verticalArrangement = Arrangement.SpaceBetween,
                            horizontalAlignment = Alignment.CenterHorizontally
                        )
                        {
                            Text(text = status.toString())
                            Button(onClick = { stopRecording()
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
                                        startActivity(intent)
                                    }
                                } catch (e: java.lang.Exception) {
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

    val loginLongClickListener = View.OnLongClickListener {
        TelegramPassport.showAppInstallAlert(this@MainActivity)
        true
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











