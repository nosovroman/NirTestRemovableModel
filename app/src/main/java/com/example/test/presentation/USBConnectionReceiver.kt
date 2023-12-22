package com.example.test.presentation

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.hardware.usb.UsbManager
import android.util.Log

class USBConnectionReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action
        when (action) {
            UsbManager.ACTION_USB_DEVICE_ATTACHED -> {
                USBConnectionObserver.notifyUSBConnected()
                Log.d("USBConnectionReceiver", "Устройство подключено через USB")
            }
            UsbManager.ACTION_USB_DEVICE_DETACHED -> {
                USBConnectionObserver.notifyUSBDisconnected()
                Log.d("USBConnectionReceiver", "Устройство отключено через USB")
            }
        }
    }
}