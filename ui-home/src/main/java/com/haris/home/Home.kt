package com.haris.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.haris.home.data.RestaurantEntity

@Composable
fun Home(navigate: (String) -> Unit) {
    Home(viewModel = hiltViewModel(), navigate = navigate)
}

@Composable
private fun Home(viewModel: SensorsViewModel, navigate: (String) -> Unit) {
    val state = viewModel.state.collectAsState().value

    Scaffold {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            HandleState(
                state = state,
                navigate = navigate,
                retry = { viewModel.retry() }
            )
        }
    }
}

@Composable
internal fun HandleState(state: SensorsViewState, navigate: (String) -> Unit, retry: () -> Unit) {
    when (state) {
        is SensorsViewState.Success -> {
            Success(state = state, navigate = navigate)
        }

        is SensorsViewState.Error -> {
            Error(state = state, navigate = navigate, retry = retry)
        }

        is SensorsViewState.Loading -> {
            Loading(state = state, navigate = navigate)
        }

        is SensorsViewState.Empty -> {}
    }
}

@Composable
private fun Success(state: SensorsViewState.Success, navigate: (String) -> Unit) {
    Sensors(state.sensors, navigate)
}

@Composable
private fun Error(
    state: SensorsViewState.Error,
    navigate: (String) -> Unit,
    retry: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (state.sensors != null) {
            Sensors(state.sensors, navigate)
        } else {
            Spacer(modifier = Modifier.height(32.dp))
        }

        Text(
            modifier = Modifier.padding(16.dp),
            text = state.message
        )
        Button(onClick = retry) {
            Text(text = "Retry")
        }
    }
}

@Composable
private fun Loading(
    state: SensorsViewState.Loading,
    navigate: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (state.sensors != null) {
            Sensors(state.sensors, navigate)
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

@Composable
private fun Sensors(sensors: List<RestaurantEntity>, navigate: (String) -> Unit) {
    LazyColumn(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
        items(items = sensors, key = { it.id }) {
            Item(item = it, navigate = navigate)
        }
    }
}

@Composable
private fun Item(item: RestaurantEntity, navigate: (String) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        onClick = { navigate(item.id) }
    ) {
        Text(modifier = Modifier.padding(8.dp), text = item.name)
    }
}