package com.haris.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.haris.resources.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SliceTopAppBar() {
    TopAppBar(
        title = {
            Column {
                Text(
                    text = stringResource(id = R.string.pickup_near),
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "13th St",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
    )
}