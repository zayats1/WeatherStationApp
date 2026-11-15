package com.bogdandev.weatherstationapp.ui


import android.bluetooth.BluetoothDevice
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bogdandev.weatherstationapp.app.BluetoothUIState
import com.bogdandev.weatherstationapp.ble.BluetoothDeviceDomain


@Composable
fun BluetoothDeviceScreen(
    modifier: Modifier = Modifier,
    state: BluetoothUIState,
    onStartScan: () -> Unit,
    onStopScan: () -> Unit,
    navController: NavController = rememberNavController(),
) {
    Surface(modifier) {
        Column(
            modifier.padding(top = 45.dp)
        )
        {
            Column(modifier.fillMaxSize()) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                )
                {
                    Button(onClick = onStartScan) {
                        Text(text = "Start scan")
                    }
                    Button(onClick = onStopScan) {
                        Text(text = "Stop scan")
                    }
                }
                BluetoothDeviceList(
                    pairedDevices = state.pairedDevices,
                    scannedDevices = state.scannedDevices,
                )

                GoBack(
                    modifier = modifier,
                    navController = navController
                )

            }
        }
    }
}


@Composable
fun BluetoothDeviceList(
    pairedDevices: List<BluetoothDeviceDomain>,
    scannedDevices: List<BluetoothDeviceDomain>,
    modifier: Modifier = Modifier

) {
    LazyColumn(
        modifier = modifier
    ) {
        item {
            Text(
                text = "Paired Devices",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier,
            )
        }
        items(
            items = pairedDevices,
            key = { it.address },
        ) { device ->
            DeviceRow(
                name = device.name ?: "Unknown device",
                address = device.address,
                onClick = { device },
            )
        }

        item(key = "scanned_header") {
            Text(
                text = "Scanned Devices",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp),
            )
        }

        items(
            items = scannedDevices,
            key = { it.address },
        ) { device ->
            DeviceRow(
                name = device.name ?: "Unknown device",
                address = device.address,
                onClick = { device },
            )
        }

    }
}

    @Composable
    fun DeviceRow(
        name: String,
        address: String,
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .clickable(onClick = onClick)
                .padding(vertical = 8.dp, horizontal = 4.dp),
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.titleMedium,
            )
            Text(
                text = address,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }

