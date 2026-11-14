package com.bogdandev.weatherstationapp.ble

import android.bluetooth.BluetoothManager
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class BluetoothController (private val context: Context){
    private val bluetoothManager by lazy {
        context.getSystemService(BluetoothManager::class.java)
    }

    private val bluetoothAdapter by lazy {
        bluetoothManager?.adapter
    }

    private fun updatePairedDevices() {
        bluetoothAdapter?.bondedDevices?.map {

        }
    }

    var scannedDevices  by mutableStateOf<List<BluetoothDeviceDomain>>(emptyList()) // mutable in class only
        private set

    var pairedDevices  by mutableStateOf<List<BluetoothDeviceDomain>>(emptyList())
        private set


}