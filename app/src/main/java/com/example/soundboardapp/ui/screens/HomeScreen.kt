package com.example.soundboardapp.ui.screens

import android.annotation.SuppressLint
import android.content.ContentValues
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.soundboardapp.R
import com.example.soundboardapp.model.Soundboard
import com.example.soundboardapp.ui.viewmodel.HomeUiState
import com.example.soundboardapp.ui.viewmodel.HomeViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = viewModel()
) {
    val homeUiState by homeViewModel.uiState.collectAsState()

    Scaffold(
        topBar = { HomeTopBar(homeViewModel, homeUiState) },
        bottomBar = { HomeBottomBar() },
    ) { it ->
        LazyColumn(
            contentPadding = it
        ) {
            if (!homeViewModel.activeSearch) {
                items(homeUiState.soundboardList) { soundboard ->
                    SoundboardCard(soundboard)
                }
            } else {
                if (homeViewModel.searchResults.isNotEmpty()) {
                    item {
                        Text(
                            "Results",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                    items(homeViewModel.searchResults) { soundboard ->
                        SoundboardCard(soundboard)
                    }
                } else {
                    item {
                        Text("No matches found")
                    }
                }
            }

        }
    }
}


@Composable
fun SoundboardCard(
    soundboard: Soundboard
) {
    Card(
        shape = RectangleShape,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(1 / 9f)
            .padding(4.dp)
            .clickable {}
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Image(
                modifier = Modifier
                    .size(64.dp)
                    .clip(RectangleShape),
                contentScale = ContentScale.Crop,
                painter = painterResource(id = soundboard.imgSrc),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = soundboard.soundboardName,
                fontSize = 16.sp,
                style = MaterialTheme.typography.labelSmall
            )
        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    homeViewModel: HomeViewModel,
    homeUiState: HomeUiState
) {
    TopAppBar(
        title = { },
        navigationIcon = {},
        actions = {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()

            ) {
                SearchBar(homeViewModel, homeUiState)
                AddSoundboardButton { homeViewModel.onAddClick() }
            }
        },
        colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = MaterialTheme.colorScheme.onPrimary)
    )
}

@Composable
fun HomeBottomBar() {
    BottomAppBar() {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Settings,
                    contentDescription = null
                )
            }

            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Home,
                    contentDescription = null
                )
            }

            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.outline_add_comment_24),
                    contentDescription = null
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(
    homeViewModel: HomeViewModel,
    homeUiState: HomeUiState
) {
    var showClearButton by rememberSaveable { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        shape = RoundedCornerShape(50),
        value = homeViewModel.searchText,
        onValueChange = { homeViewModel.onSearchTextChanged(it) },
        modifier = Modifier
            .size(width = 330.dp, height = 50.dp),
        placeholder = { Text("Search...") },
        leadingIcon = {
            IconButton(
                onClick = {}
            ) {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = null
                )
            }
        },
        trailingIcon = {
            AnimatedVisibility(
                visible = homeViewModel.activeSearch,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                IconButton(
                    onClick = { homeViewModel.onClearClick() },
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Clear,
                        contentDescription = null
                    )
                }
            }

        },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() })
    )
}

@Composable
fun AddSoundboardButton(
    onAddClick: () -> Unit
) {
    IconButton(
        onClick = onAddClick,
    ) {
        Icon(
            Icons.Outlined.Add,
            contentDescription = null,
            tint = Color.Black,
            modifier = Modifier.size(30.dp)
        )
    }
}

@Preview
@Composable
fun test() {
    HomeScreen()
}
