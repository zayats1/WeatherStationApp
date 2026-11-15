package com.bogdandev.weatherstationapp.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bogdandev.weatherstationapp.ble.BluetoothController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn


import javax.inject.Inject

const val TIMEOUT : Long = 5000
@HiltViewModel
class BluetoothViewModel @Inject constructor(
    private val bluetoothController: BluetoothController
)
    : ViewModel() {
        private val _state = MutableStateFlow(BluetoothUIState())
       val state = combine(
           bluetoothController.scannedDevices,
           bluetoothController.pairedDevices,
           _state
       ) { scannedDevices, pairedDevices, state ->
           state.copy(
               scannedDevices = scannedDevices,
               pairedDevices = pairedDevices
           )

       }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(TIMEOUT), _state.value)

    fun startScan() = bluetoothController.startDiscovery()
    fun stopScan() = bluetoothController.stopDiscovery()
}