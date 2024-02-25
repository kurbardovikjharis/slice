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
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.haris.resources.R
import com.haris.restaurantdetails.data.MenuItemEntity

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
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(
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
                },
                scrollBehavior = scrollBehavior,
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
                isCollapsed = scrollBehavior.state.collapsedFraction == 1f,
                retry = { viewModel.retry() })
        }
    }
}

@Composable
internal fun HandleState(
    state: RestaurantDetailsViewState,
    isCollapsed: Boolean,
    retry: () -> Unit
) {
    when (state) {
        is RestaurantDetailsViewState.Success -> {
            Success(state, isCollapsed)
        }

        is RestaurantDetailsViewState.Error -> {
            Error(state, isCollapsed, retry)
        }

        is RestaurantDetailsViewState.Loading -> {
            Loading(state, isCollapsed)
        }

        is RestaurantDetailsViewState.Empty -> {}
    }
}

@Composable
private fun Success(state: RestaurantDetailsViewState.Success, isCollapsed: Boolean) {
    Content(state.data, isCollapsed)
}

@Composable
private fun Error(
    state: RestaurantDetailsViewState.Error,
    isCollapsed: Boolean,
    retry: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (state.data != null) {
            Content(state.data, isCollapsed)
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
    state: RestaurantDetailsViewState.Loading,
    isCollapsed: Boolean
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (state.data != null) {
            Content(state.data, isCollapsed)
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
private fun Content(data: RestaurantDetailsEntity, isCollapsed: Boolean) {
    LazyColumn {
        stickyHeader {
            Header(data, isCollapsed)
        }

        items(items = data.menuItems, key = { it.id }) {
            MenuItem(it)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Header(data: RestaurantDetailsEntity, isCollapsed: Boolean) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                if (isCollapsed) TopAppBarDefaults.largeTopAppBarColors().scrolledContainerColor
                else MaterialTheme.colorScheme.background
            )
            .padding(16.dp),
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

@Composable
private fun MenuItem(item: MenuItemEntity) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(text = item.title, style = MaterialTheme.typography.displaySmall)
        Spacer(modifier = Modifier.height(16.dp))

        item.items.forEach {
            Column {
                Text(text = it.title, style = MaterialTheme.typography.titleLarge)
                if (it.description.isNotEmpty()) {
                    Text(text = it.description, style = MaterialTheme.typography.titleMedium)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = it.price, style = MaterialTheme.typography.titleMedium)

                Spacer(modifier = Modifier.height(16.dp))
                HorizontalDivider()
                Spacer(modifier = Modifier.height(16.dp))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}
