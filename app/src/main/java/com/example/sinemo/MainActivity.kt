package com.example.sinemo

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.app.AlertDialog
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
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.sinemo.ui.theme.SinemoTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            var imageChangeBroadcastReceiver: ImageChangeBroadcastReceiver? = null
            // register a receiver to tell the MainActivity when a notification has been received
            imageChangeBroadcastReceiver = ImageChangeBroadcastReceiver()
            val intentFilter = IntentFilter()
            intentFilter.addAction("com.example.sinemo")
            registerReceiver(imageChangeBroadcastReceiver, intentFilter)

/////////////////////////////////////RUNNING_APP//////////////////////////////////
            val scaffoldState = rememberScaffoldState()
            val scope = rememberCoroutineScope()
            val runningProcessInfo = mutableListOf<String>()
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
            /////////////////////^^^^^^^^^^^^^RUNNING_APP^^^^^^^^^/////////////////////////
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
                                        imageVector = Icons.Default.Send,
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
                            Text(text = runningProcessInfo.toString())
                            Text(text = imageChangeBroadcastReceiver?.startrec.toString())}
                    }
                )
            }
        }
    }
}











