package com.haris.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.haris.data.Restaurant
import com.haris.resources.R

@Composable
fun Home(navigate: (String) -> Unit) {
    Home(viewModel = hiltViewModel(), navigate = navigate)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Home(viewModel: SensorsViewModel, navigate: (String) -> Unit) {
    val state = viewModel.state.collectAsState().value

    Scaffold(
        topBar = {
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
    ) {
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
    Restaurants(state.restaurants, navigate)
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
        if (state.restaurants != null) {
            Restaurants(state.restaurants, navigate)
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
    state: SensorsViewState.Loading,
    navigate: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (state.restaurants != null) {
            Restaurants(state.restaurants, navigate)
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
private fun Restaurants(sensors: List<Restaurant>, navigate: (String) -> Unit) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
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
private fun Item(item: Restaurant, navigate: (String) -> Unit) {
    Card(
        onClick = { navigate(item.id) },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(bottom = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AsyncImage(
                model = item.url,
                contentDescription = stringResource(id = R.string.restaurant),
                modifier =
                Modifier
                    .height(250.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Text(
                text = item.name,
                modifier = Modifier.padding(horizontal = 8.dp),
                style = MaterialTheme.typography.titleSmall
            )
            Row(
                modifier = Modifier.padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.outline_kid_star_24),
                    contentDescription = stringResource(id = R.string.star),
                    tint = Color.Unspecified,
                )
                Text(
                    text = item.rating,
                    modifier = Modifier.padding(horizontal = 4.dp),
                    style = MaterialTheme.typography.labelSmall
                )
                Icon(
                    painter = painterResource(R.drawable.baseline_album_4),
                    contentDescription = stringResource(id = R.string.separator),
                    tint = Color.Unspecified,
                )
                Text(
                    text = item.time,
                    modifier = Modifier.padding(horizontal = 4.dp),
                    style = MaterialTheme.typography.labelSmall
                )
                Icon(
                    painter = painterResource(R.drawable.baseline_album_4),
                    contentDescription = stringResource(id = R.string.separator),
                    tint = Color.Unspecified,
                )
                Text(
                    text = item.distance,
                    modifier = Modifier.padding(horizontal = 4.dp),
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    }
}