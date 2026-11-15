package com.bogdandev.weatherstationapp

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bogdandev.weatherstationapp.app.BluetoothViewModel
import com.bogdandev.weatherstationapp.app.WeatherStationViewModel
import com.bogdandev.weatherstationapp.ui.BluetoothDeviceScreen
import com.bogdandev.weatherstationapp.ui.MainScreen
import com.bogdandev.weatherstationapp.ui.Screen
import com.bogdandev.weatherstationapp.ui.SettingsScreen
import com.bogdandev.weatherstationapp.ui.theme.TheAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val bluetoothManager by lazy {
        applicationContext.getSystemService(BluetoothManager::class.java)
    }

    private val bluetoothAdapter by lazy {
        bluetoothManager?.adapter
    }

    private val isBluetoothEnabled: Boolean
        get() = bluetoothAdapter?.isEnabled == true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val enableBluetoothLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {

        }

        val permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { perms ->
            val canEnableBluetooth = perms[Manifest.permission.BLUETOOTH_CONNECT] == true

            if (canEnableBluetooth && !isBluetoothEnabled) {
                enableBluetoothLauncher.launch(
                    Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                )
            }

        }

        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.BLUETOOTH_SCAN,
                Manifest.permission.BLUETOOTH_CONNECT
            )
        )

        setContent {
            val model: WeatherStationViewModel = hiltViewModel<WeatherStationViewModel>()
            val bluetoothModel = hiltViewModel<BluetoothViewModel>()
            val navController = rememberNavController()

            TheAppTheme {
                LaunchedEffect(
                    permissionLauncher.launch(
                        arrayOf(
                            Manifest.permission.BLUETOOTH_SCAN,
                            Manifest.permission.BLUETOOTH_CONNECT
                        )
                    )
                ) { }
                NavHost(
                    navController = navController,
                    startDestination = Screen.MAIN.toString()
                ) {
                    composable(route = Screen.MAIN.toString()) {
                        MainScreen(
                            modifier = Modifier,
                            model = model,
                            navController = navController
                        )
                    }
                    composable(route = Screen.SETTINGS.toString()) {
                        SettingsScreen(
                            modifier = Modifier,
                            model = model,
                            navController = navController
                        )
                    }
                    composable(route = Screen.BLUETOOTH_SCREEN.toString()) {
                        val state by bluetoothModel.state.collectAsState()
                            BluetoothDeviceScreen(
                                modifier = Modifier,
                                state = state,
                                onStartScan = bluetoothModel::startScan,
                                onStopScan = bluetoothModel::stopScan,
                                navController = navController,
                            )
                    }
                }
            }

        }
    }
}


