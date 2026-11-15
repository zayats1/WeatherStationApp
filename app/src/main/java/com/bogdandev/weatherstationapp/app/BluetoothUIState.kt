package com.bogdandev.weatherstationapp.app


import com.bogdandev.weatherstationapp.ble.BluetoothDeviceDomain

data class BluetoothUIState(
    val scannedDevices:List<BluetoothDeviceDomain> = emptyList(),
    val pairedDevices : List<BluetoothDeviceDomain> = emptyList(),
)
