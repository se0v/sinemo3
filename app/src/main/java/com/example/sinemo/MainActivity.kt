package com.example.sinemo
import android.annotation.SuppressLint
import android.app.*
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.rememberNavController
import com.example.sinemo.navigation.AppNavigation
import com.example.sinemo.navigation.AppScreen
import com.example.sinemo.ui.theme.SinemoTheme
import org.telegram.passport.*

@Suppress("DEPRECATION")
class MainActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition", "NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val imageChangeBroadcastReceiver: ImageChangeBroadcastReceiver?
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
                            }
                        )
                    },
                    content = {
                        AppNavigation(navController)
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
    @Deprecated("Deprecated in Java")
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
}
}











