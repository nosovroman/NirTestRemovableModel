package com.example.test.presentation

import java.util.*

object USBConnectionObserver {
    private val observers: MutableSet<USBConnectionObserverListener> = HashSet()

    fun addObserver(observer: USBConnectionObserverListener) {
        observers.add(observer)
    }

    fun removeObserver(observer: USBConnectionObserverListener) {
        observers.remove(observer)
    }

    fun notifyUSBConnected() {
        for (observer in observers) {
            observer.onUSBConnected()
        }
    }

    fun notifyUSBDisconnected() {
        for (observer in observers) {
            observer.onUSBDisconnected()
        }
    }
}