package com.example.sinemo
import android.annotation.SuppressLint
import android.app.*
import android.app.Activity.RESULT_OK
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.core.content.FileProvider
import androidx.navigation.compose.rememberNavController
import com.example.sinemo.navigation.AppNavigation
import com.example.sinemo.navigation.AppScreen
import com.example.sinemo.ui.theme.SinemoTheme
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
            val screens = AppScreen.getAll()
            var selectedScreen by remember {
                mutableStateOf(screens.first())
            }

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
                    },
                    bottomBar = {
                        BottomNavigation {
                            screens.forEach { screen ->
                                BottomNavigationItem(
                                    selected = selectedScreen == screen,
                                    onClick = {
                                        selectedScreen = screen
                                        navController.navigate(screen.route)
                                    },
                                    icon = {
                                        Icon(imageVector = screen.icon,
                                            contentDescription = null)
                                    },
                                    label = {
                                        Text(text = stringResource(screen.nameResource))
                                    },
                                    alwaysShowLabel = false

                                )
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











