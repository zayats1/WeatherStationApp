package com.bogdandev.weatherstationapp.ble

import android.Manifest
import android.bluetooth.BluetoothDevice
import androidx.annotation.RequiresPermission


@RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
fun BluetoothDevice.toDomain(): BluetoothDeviceDomain {
    return BluetoothDeviceDomain(
        name = name,
        address = address
    )
}