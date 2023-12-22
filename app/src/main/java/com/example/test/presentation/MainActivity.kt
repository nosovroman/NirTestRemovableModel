package com.example.test.presentation

import android.content.Intent
import android.content.IntentFilter
import android.hardware.usb.UsbManager
import android.os.Bundle
import android.os.StrictMode
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.test.presentation.ui.theme.TestTheme


class MainActivity : ComponentActivity() {

    private val usbConnectionReceiver = USBConnectionReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder().permitAll().build())
        StrictMode.allowThreadDiskReads()
        super.onCreate(savedInstanceState)

        // Регистрация BroadcastReceiver
        val filter = IntentFilter()
            .apply {
                addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED)
                addAction(UsbManager.ACTION_USB_DEVICE_DETACHED)
            }
        registerReceiver(usbConnectionReceiver, filter)

        val viewModel = ViewModelProvider(this).get(DbViewModel::class.java)

        //Runtime.getRuntime().exec("su")

        setContent {
            TestTheme {
                MessengerScreen(context = this, viewModel = viewModel)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(usbConnectionReceiver)
    }
}