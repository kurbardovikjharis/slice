package com.haris.restaurantdetails

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.haris.resources.R

@Composable
fun RestaurantDetails(navigateUp: () -> Unit) {
    RestaurantDetails(viewModel = hiltViewModel(), navigateUp = navigateUp)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RestaurantDetails(
    viewModel: RestaurantDetailsViewModel,
    navigateUp: () -> Unit
) {
    val state = viewModel.state.collectAsState().value

    Scaffold(
        topBar = {
            LargeTopAppBar(
                modifier = Modifier.background(MaterialTheme.colorScheme.primary),
                title = {
                    Text(text = stringResource(id = R.string.restaurant_details))
                },
                navigationIcon = {
                    IconButton(onClick = navigateUp) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                            contentDescription = stringResource(id = R.string.back)
                        )
                    }
                }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            HandleState(state = state, retry = { viewModel.retry() })
        }
    }
}

@Composable
internal fun HandleState(state: RestaurantDetailsViewState, retry: () -> Unit) {
    when (state) {
        is RestaurantDetailsViewState.Success -> {
            Success(state)
        }

        is RestaurantDetailsViewState.Error -> {
            Error(state, retry)
        }

        is RestaurantDetailsViewState.Loading -> {
            Loading(state)
        }

        is RestaurantDetailsViewState.Empty -> {}
    }
}

@Composable
private fun Success(state: RestaurantDetailsViewState.Success) {
    Content(state.data)
}

@Composable
private fun Error(
    state: RestaurantDetailsViewState.Error,
    retry: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (state.data != null) {
            Content(state.data)
        } else {
            Spacer(modifier = Modifier.height(32.dp))
        }

        Text(
            modifier = Modifier.padding(16.dp),
            text = state.message
        )
        Button(onClick = retry) {
            Text(text = stringResource(id = R.string.retry))
        }
    }
}

@Composable
private fun Loading(
    state: RestaurantDetailsViewState.Loading
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (state.data != null) {
            Content(state.data)
        } else {
            Spacer(modifier = Modifier.height(32.dp))
        }

        CircularProgressIndicator(
            modifier = Modifier
                .size(50.dp)
                .testTag("progress"),
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Content(data: RestaurantDetailsEntity) {
    LazyColumn {
        stickyHeader {
            Header(data)
        }
    }
}

@Composable
private fun Header(data: RestaurantDetailsEntity) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(text = data.name, style = MaterialTheme.typography.titleLarge)
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.outline_kid_star_24),
                contentDescription = stringResource(id = R.string.star),
                tint = Color.Unspecified
            )
            Text(text = data.rating, style = MaterialTheme.typography.labelLarge)
        }
    }
}
