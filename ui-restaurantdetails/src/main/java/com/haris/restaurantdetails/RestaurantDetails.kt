package com.haris.restaurantdetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.haris.resources.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantDetails(navigateUp: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.restaurant_details))
                },
                navigationIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                        contentDescription = stringResource(id = R.string.back)
                    )
                }
            )
        }
    ) {
        Box(modifier = Modifier.padding(it))
    }
}