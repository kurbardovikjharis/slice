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
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.haris.data.entities.MenuSubItem
import com.haris.resources.R
import com.haris.restaurantdetails.data.MenuItemEntity
import kotlinx.coroutines.launch

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
            if (state is RestaurantDetailsViewState.Success && state.isSearching) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    TextField(
                        modifier = Modifier.weight(1f),
                        value = state.term,
                        onValueChange = { viewModel.search(it) },
                        label = {
                            Text(text = stringResource(id = R.string.find_menus))
                        },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_search_24),
                                contentDescription = stringResource(id = R.string.search)
                            )
                        },
                    )
                    TextButton(onClick = { viewModel.isSearching(false) }) {
                        Text(
                            text = stringResource(id = R.string.cancel),
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                }
            } else {
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
                retry = { viewModel.retry() },
                onSearchClicked = { viewModel.isSearching(true) })
        }
    }
}

@Composable
internal fun HandleState(
    state: RestaurantDetailsViewState,
    isCollapsed: Boolean,
    retry: () -> Unit,
    onSearchClicked: () -> Unit
) {
    when (state) {
        is RestaurantDetailsViewState.Success -> {
            Success(
                state = state,
                isCollapsed = isCollapsed,
                onSearchClicked = onSearchClicked
            )
        }

        is RestaurantDetailsViewState.Error -> {
            Error(
                state = state,
                isCollapsed = isCollapsed,
                retry = retry,
                onSearchClicked = onSearchClicked
            )
        }

        is RestaurantDetailsViewState.Loading -> {
            Loading(
                state = state,
                isCollapsed = isCollapsed,
                onSearchClicked = onSearchClicked
            )
        }

        is RestaurantDetailsViewState.Empty -> {}
    }
}

@Composable
private fun Success(
    state: RestaurantDetailsViewState.Success,
    isCollapsed: Boolean,
    onSearchClicked: () -> Unit
) {
    Content(
        data = state.data,
        isCollapsed = isCollapsed,
        isSearching = state.isSearching,
        onSearchClicked = onSearchClicked
    )
}

@Composable
private fun Error(
    state: RestaurantDetailsViewState.Error,
    isCollapsed: Boolean,
    retry: () -> Unit,
    onSearchClicked: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (state.data != null) {
            Content(
                data = state.data,
                isCollapsed = isCollapsed,
                isSearching = false,
                onSearchClicked = onSearchClicked
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
    state: RestaurantDetailsViewState.Loading,
    isCollapsed: Boolean,
    onSearchClicked: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (state.data != null) {
            Content(
                data = state.data,
                isCollapsed = isCollapsed,
                isSearching = false,
                onSearchClicked = onSearchClicked
            )
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
private fun Content(
    data: RestaurantDetailsEntity,
    isCollapsed: Boolean,
    isSearching: Boolean,
    onSearchClicked: () -> Unit
) {
    val lazyListState = rememberLazyListState()
    LazyColumn(state = lazyListState) {
        if (!isSearching) {
            item {
                Header(data, isCollapsed)
            }
            stickyHeader {
                StickyHeader(
                    menuItem = data.menuItems,
                    isCollapsed = isCollapsed,
                    lazyListState = lazyListState,
                    onSearchClicked = onSearchClicked
                )
            }
        }

        if (isSearching) {
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
            items(items = data.searchedMenu, key = { it.id }) {
                SearchedMenuSubItem(item = it)
            }
        } else {
            itemsIndexed(
                items = data.menuItems,
                key = { _, item -> item.id }
            ) { index, item ->
                MenuItem(item, index + 1 == data.menuItems.size)
            }
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

            val ratingsString = stringResource(id = R.string.ratings)
            val rating = "${data.rating} (${data.numberOfRatings} $ratingsString)"
            Text(text = rating, style = MaterialTheme.typography.labelLarge)
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_commute_24),
                contentDescription = stringResource(id = R.string.commute),
                tint = Color.Unspecified
            )

            Text(text = data.time, style = MaterialTheme.typography.labelLarge)

            Icon(
                painter = painterResource(id = R.drawable.baseline_album_4),
                contentDescription = stringResource(id = R.string.separator),
                tint = Color.Unspecified
            )

            Text(text = data.distance, style = MaterialTheme.typography.labelLarge)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_access_time_filled_24),
                    contentDescription = stringResource(id = R.string.clock),
                    tint = Color.Unspecified
                )

                Text(text = data.time, style = MaterialTheme.typography.labelLarge)
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.about),
                    style = MaterialTheme.typography.labelLarge
                )
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_forward_ios_24),
                    contentDescription = stringResource(id = R.string.arrow_forward),
                    tint = Color.Unspecified
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun StickyHeader(
    menuItem: List<MenuItemEntity>,
    isCollapsed: Boolean,
    lazyListState: LazyListState,
    onSearchClicked: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                if (isCollapsed) TopAppBarDefaults.largeTopAppBarColors().scrolledContainerColor
                else MaterialTheme.colorScheme.background
            )
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onSearchClicked) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_search_24),
                contentDescription = stringResource(id = R.string.search)
            )
        }
        val pxValue = LocalDensity.current.run { 52.dp.toPx() }
        menuItem.forEachIndexed { index, item ->
            TextButton(
                onClick = {
                    coroutineScope.launch {
                        lazyListState.animateScrollToItem(
                            index = index + 2,
                            scrollOffset = -pxValue.toInt()
                        )
                    }
                }
            ) {
                Text(text = item.title, style = MaterialTheme.typography.labelLarge)
            }
        }
    }
}

@Composable
private fun MenuItem(item: MenuItemEntity, isLastItem: Boolean) {
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

        if (!isLastItem) {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun SearchedMenuSubItem(item: MenuSubItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = item.title,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = item.price,
                style = MaterialTheme.typography.labelSmall
            )

            item.description?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    }

    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
}
