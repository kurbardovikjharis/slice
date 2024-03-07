package com.haris.grouprestaurants.ui

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.haris.data.entities.Restaurant
import com.haris.grouprestaurants.GroupRestaurantsViewModel
import com.haris.grouprestaurants.GroupRestaurantsViewState
import com.haris.resources.R

@Composable
fun GroupRestaurants(
    navigateUp: () -> Unit,
    navigateToRestaurantDetails: (String) -> Unit
) {
    GroupRestaurants(
        viewModel = hiltViewModel(),
        navigateUp = navigateUp,
        navigateToRestaurantDetails = navigateToRestaurantDetails
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun GroupRestaurants(
    viewModel: GroupRestaurantsViewModel,
    navigateUp: () -> Unit,
    navigateToRestaurantDetails: (String) -> Unit
) {
    val state = viewModel.state.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = state.title)
                },
                navigationIcon = {
                    IconButton(onClick = navigateUp) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                            contentDescription = stringResource(id = R.string.back)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors().copy(
                    containerColor = TopAppBarDefaults.largeTopAppBarColors().scrolledContainerColor
                )
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
                retry = { viewModel.retry() },
                navigate = navigateToRestaurantDetails
            )
        }
    }
}

@Composable
internal fun HandleState(
    state: GroupRestaurantsViewState,
    retry: () -> Unit,
    navigate: (String) -> Unit
) {
    when (state) {
        is GroupRestaurantsViewState.Success -> {
            Success(state, navigate)
        }

        is GroupRestaurantsViewState.Error -> {
            Error(
                state = state,
                retry = retry,
                navigate = navigate
            )
        }

        is GroupRestaurantsViewState.Loading -> {
            Loading(state, navigate)
        }

        is GroupRestaurantsViewState.Empty -> {}
    }
}

@Composable
private fun Success(
    state: GroupRestaurantsViewState.Success,
    navigate: (String) -> Unit
) {
    Content(state.restaurants, navigate)
}

@Composable
private fun Error(
    state: GroupRestaurantsViewState.Error,
    retry: () -> Unit,
    navigate: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (state.restaurants != null) {
            Content(state.restaurants, navigate)
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
    state: GroupRestaurantsViewState.Loading,
    navigate: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (state.restaurants != null) {
            Content(state.restaurants, navigate)
        } else {
            Loading()
        }
    }
}

@Composable
private fun Content(
    data: List<Restaurant>,
    navigate: (String) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
        items(items = data, key = { it.id }) {
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
            Box {
                AsyncImage(
                    model = item.url,
                    contentDescription = stringResource(id = R.string.restaurant),
                    modifier = Modifier
                        .height(150.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.BottomStart),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = item.name,
                        modifier = Modifier.padding(horizontal = 8.dp),
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.background
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
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.background
                        )
                    }
                }
            }
            Row(
                modifier = Modifier.padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.baseline_access_time_filled_24),
                    contentDescription = stringResource(id = R.string.star),
                    tint = Color.Unspecified,
                )
                Text(
                    text = item.time,
                    modifier = Modifier.padding(horizontal = 4.dp),
                    style = MaterialTheme.typography.labelSmall
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