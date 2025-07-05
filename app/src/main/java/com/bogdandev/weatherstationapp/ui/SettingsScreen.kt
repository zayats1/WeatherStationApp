package com.bogdandev.weatherstationapp.ui


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bogdandev.weatherstationapp.R
import com.bogdandev.weatherstationapp.app.WeatherStationViewModel
import com.bogdandev.weatherstationapp.data.SavedProviders

@Preview(showBackground = true)
@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier, model: WeatherStationViewModel = WeatherStationViewModel(),
    navController: NavController = rememberNavController()
) {
    Surface(modifier) {
        Column(
            modifier.padding(top = 45.dp)
        ) {
            SelectUnits(
                modifier = modifier,
                model = model
            )
            ConnectionInfo(modifier = modifier, model = model)
            GoBack(
                modifier = modifier,
                navController = navController
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsPreview() {
    Surface(
        modifier = Modifier
            .padding(top = 25.dp)
            .height(2000.dp)
            .width(1080.dp)
    ) {
        SettingsScreen(modifier = Modifier)
    }
}


@Preview
@Composable
fun SelectUnits(
    modifier: Modifier = Modifier,
    model: WeatherStationViewModel = WeatherStationViewModel()
) {
    val isSi by model.isSi.collectAsStateWithLifecycle()
    DisplayBar(modifier = modifier) {
        Text(
            text = "Select units!",
            modifier = modifier.padding(start = 10.dp, end = 20.dp)
        )
        IconButton(
            onClick = {
                model.selectSi()
            },
            modifier = modifier
                .size(150.dp)
                .padding(end = 2.dp)
                .clip(CircleShape)
        )
        {
            Icon(
                painter = painterResource(
                    R.drawable.celsius_foreground
                ),
                tint = if (isSi) {
                    Color.Green
                } else {
                    Color.Red
                },


                contentDescription = null
            )
        }
        IconButton(
            onClick = {
                model.selectImperial()
            },
            modifier = modifier
                .size(150.dp)
                .padding(end = 2.dp)
                .clip(CircleShape)

        ) {
            Icon(
                painter = painterResource(
                    R.drawable.fahrenheit_foreground
                ),
                tint = if (!isSi) {
                    Color.Green
                } else {
                    Color.Red
                },
                modifier = modifier
                    .size(150.dp)
                    .padding(end = 2.dp)
                    .clip(CircleShape),

                contentDescription = null
            )
        }
    }
}


@Preview
@Composable
fun GoBack(modifier: Modifier = Modifier, navController: NavController = rememberNavController()) {
    DisplayBar(modifier = modifier) {
        Text(
            text = "Return!",
            modifier = modifier.padding(start = 10.dp, end = 20.dp)
        )
        Button(
            onClick = {
                navController.navigate(Screen.MAIN.toString())
            },
            modifier = modifier
                .size(150.dp)
                .padding(end = 2.dp)
                .clip(CircleShape),
            colors = ButtonColors(
                contentColor = Color.Magenta,
                disabledContainerColor = Color.Transparent,
                disabledContentColor = Color.Transparent,
                containerColor = Color.Transparent,
            ),

            ) {
            Image(
                painter = painterResource(
                    R.drawable.sun_foreground
                ),
                modifier = modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(Color.Blue)
                    .padding(end = 2.dp),


                contentDescription = null
            )
        }
    }
}


class  Expander(expanded: Boolean){
    private var isExpanded  = expanded

    fun close(){
        isExpanded = false
    }
    fun toggle(){
        isExpanded = !isExpanded
    }
    fun get(): Boolean{
        return isExpanded
    }
}
@Preview
@Composable
fun ConnectionInfo(
    modifier: Modifier = Modifier,
    model: WeatherStationViewModel = WeatherStationViewModel(),
) {
    val info = model.savedIP.collectAsStateWithLifecycle().value
    var expander by remember { mutableStateOf(Expander(false)) }
    val ips = model.getIPs(LocalContext.current)
    DisplayBar(modifier = modifier) {
        Text(
            text = "Connection\ninfo:",
            modifier = modifier.padding(start = 10.dp, end = 2.dp)
        )
        Text(
            text = "ssid:${info.ssid}\nip address:${info.ipaddr}",
            modifier = modifier.padding(start = 10.dp, end = 20.dp)
        )

        IconButton(onClick = { expander.toggle() }) {
            Icon(Icons.Default.MoreVert, contentDescription = "More options")
        }
        ips?.let { it -> IpsMenu(
            modifier = modifier,
            ips = it,
            expander = expander
        ) }

    }
}



@Composable
fun IpsMenu(modifier: Modifier = Modifier, ips: List<SavedProviders>, expander: Expander = Expander(false)) {
    DropdownMenu(
        modifier = modifier,
        expanded = expander.get(),
        onDismissRequest = { expander.close()}
    ) {

        Log.d("Connection Info", ips.toString())
        ips.forEach { option ->
            DropdownMenuItem(
                text = { Text(option.ipaddr.toString()) },
                onClick = { /* Do something... */ }
            )

        }
        var text by remember { mutableStateOf("Hello") }
        TextField(
            value = text,
            onValueChange = { text = it },
            maxLines = 1,
            label = { Text("Insert url") }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun IpsMenuPreview(
) {
    val ips = listOf(SavedProviders("Hi","192.168.1.1"), SavedProviders("LoremIpSon","192.168.1.2"))
    IpsMenu(ips = ips, expander = Expander(true))
}