package com.bogdandev.weatherstationapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bogdandev.weatherstationapp.R


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
