package com.haris.search.ui

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
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
import com.haris.compose.SliceTopAppBar
import com.haris.data.Group
import com.haris.data.Restaurant
import com.haris.resources.R
import com.haris.search.SensorsViewModel
import com.haris.search.SensorsViewState

@Composable
fun Search(
    navigate: (String) -> Unit,
    navigateToGroupRestaurants: (String) -> Unit
) {
    Search(
        viewModel = hiltViewModel(),
        navigate = navigate,
        navigateToGroupRestaurants = navigateToGroupRestaurants
    )
}

@Composable
private fun Search(
    viewModel: SensorsViewModel,
    navigate: (String) -> Unit,
    navigateToGroupRestaurants: (String) -> Unit
) {
    val state = viewModel.state.collectAsState().value

    Scaffold(
        topBar = { SliceTopAppBar(state.streetName) }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            HandleState(
                state = state,
                onTermChanged = { term -> viewModel.search(term) },
                navigate = navigate,
                retry = { viewModel.retry() },
                navigateToGroupRestaurants = navigateToGroupRestaurants
            )
        }
    }
}

@Composable
internal fun HandleState(
    state: SensorsViewState,
    onTermChanged: (String) -> Unit,
    navigate: (String) -> Unit,
    navigateToGroupRestaurants: (String) -> Unit,
    retry: () -> Unit
) {
    when (state) {
        is SensorsViewState.Success -> {
            Success(
                state = state,
                onTermChanged = onTermChanged,
                navigate = navigate,
                navigateToGroupRestaurants = navigateToGroupRestaurants
            )
        }

        is SensorsViewState.Error -> {
            Error(
                state = state,
                onTermChanged = onTermChanged,
                navigate = navigate,
                navigateToGroupRestaurants = navigateToGroupRestaurants,
                retry = retry
            )
        }

        is SensorsViewState.Loading -> {
            Loading(
                state = state,
                onTermChanged = onTermChanged,
                navigate = navigate,
                navigateToGroupRestaurants = navigateToGroupRestaurants
            )
        }

        is SensorsViewState.Empty -> {}
    }
}

@Composable
private fun Success(
    state: SensorsViewState.Success,
    onTermChanged: (String) -> Unit,
    navigate: (String) -> Unit,
    navigateToGroupRestaurants: (String) -> Unit
) {
    Groups(
        term = state.term,
        groups = state.groups,
        searchedRestaurants = state.searchedRestaurants,
        onTermChanged = onTermChanged,
        navigate = navigate,
        navigateToGroupRestaurants = navigateToGroupRestaurants,
    )
}

@Composable
private fun Error(
    state: SensorsViewState.Error,
    onTermChanged: (String) -> Unit,
    navigate: (String) -> Unit,
    navigateToGroupRestaurants: (String) -> Unit,
    retry: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (state.groups != null) {
            Groups(
                term = state.term,
                groups = state.groups,
                searchedRestaurants = state.searchedRestaurants,
                onTermChanged = onTermChanged,
                navigate = navigate,
                navigateToGroupRestaurants = navigateToGroupRestaurants
            )
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
    onTermChanged: (String) -> Unit,
    navigate: (String) -> Unit,
    navigateToGroupRestaurants: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (state.groups != null) {
            Groups(
                term = state.term,
                groups = state.groups,
                searchedRestaurants = state.searchedRestaurants,
                onTermChanged = onTermChanged,
                navigate = navigate,
                navigateToGroupRestaurants = navigateToGroupRestaurants
            )
        } else {
            Loading()
        }
    }
}

@Composable
private fun Groups(
    term: String,
    groups: List<Group>,
    searchedRestaurants: List<Restaurant>,
    onTermChanged: (String) -> Unit,
    navigate: (String) -> Unit,
    navigateToGroupRestaurants: (String) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        item {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                value = term,
                onValueChange = onTermChanged,
                label = {
                    Text(text = stringResource(id = R.string.find_restaurants))
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_search_24),
                        contentDescription = stringResource(id = R.string.search)
                    )
                },
                trailingIcon = {
                    if (term.isEmpty()) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_map_24),
                            contentDescription = stringResource(id = R.string.map)
                        )
                    } else {
                        IconButton(onClick = { onTermChanged("") }) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_close_24),
                                contentDescription = stringResource(id = R.string.close)
                            )
                        }
                    }
                },
            )
        }
        if (term.isEmpty()) {
            items(items = groups, key = { it.id }) {
                Group(
                    item = it,
                    navigate = navigate,
                    navigateToGroupRestaurants = navigateToGroupRestaurants
                )
            }
        } else {
            items(items = searchedRestaurants, key = { it.id }) {
                SearchedRestaurant(item = it, navigate = navigate)
            }
        }
    }
}

@Composable
private fun Group(
    item: Group,
    navigate: (String) -> Unit,
    navigateToGroupRestaurants: (String) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = item.name,
                style = MaterialTheme.typography.titleLarge
            )
            TextButton(onClick = { navigateToGroupRestaurants(item.id) }) {
                Text(
                    text = stringResource(id = R.string.view_all),
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(items = item.restaurants, key = { it.id }) {
                Restaurant(item = it, navigate = navigate)
            }
        }
    }
}

@Composable
private fun Restaurant(item: Restaurant, navigate: (String) -> Unit) {
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
                modifier = Modifier
                    .height(200.dp)
                    .width(250.dp)
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

@Composable
private fun SearchedRestaurant(
    item: Restaurant,
    navigate: (String) -> Unit
) {
    Card(
        onClick = { navigate(item.id) },
        colors = CardDefaults.cardColors()
            .copy(containerColor = MaterialTheme.colorScheme.background),
        shape = RoundedCornerShape(0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = item.distance,
                    style = MaterialTheme.typography.labelSmall
                )
                Text(
                    text = item.time,
                    style = MaterialTheme.typography.labelSmall
                )
            }
            AsyncImage(
                model = item.url,
                contentDescription = stringResource(id = R.string.restaurant),
                modifier = Modifier
                    .height(50.dp)
                    .width(50.dp),
                contentScale = ContentScale.Crop
            )
        }
    }
}