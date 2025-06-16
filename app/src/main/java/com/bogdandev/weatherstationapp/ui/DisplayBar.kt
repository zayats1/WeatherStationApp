package com.bogdandev.weatherstationapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DisplayBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.(modifier: Modifier) -> Unit
) {
    Row(
        verticalAlignment = Alignment.Companion.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .padding(5.dp)
            .padding(top = 5.dp)
            .fillMaxWidth()
            .height(150.dp)
    ) {
        content(modifier)
    }
}