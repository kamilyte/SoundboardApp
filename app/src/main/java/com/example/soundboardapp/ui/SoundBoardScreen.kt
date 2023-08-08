package com.example.soundboardapp.ui


import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.soundboardapp.model.Audio


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SoundBoardScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(
        bottomBar = {
            SoundBoardBar()
        },
        topBar = {
            SoundBoardTopBar()
        },
        containerColor = MaterialTheme.colorScheme.onPrimary
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.fillMaxSize()
        ) {
            Spacer(modifier = modifier.height(60.dp))
            Box(
                modifier = modifier
                    .width(100.dp)
                    .height(100.dp)
                    .background(Color.Black)
            )
            Spacer(modifier = modifier.height(40.dp))

            SoundBoard()
        }

    }
}



@Composable
fun BackButton() {
    TextButton(
        onClick = {},
    ) {
        Icon(
            Icons.Filled.ArrowBack,
            contentDescription = "Arrow Back",
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SoundBoardTopBar(
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text("Soundboard #1") },
        modifier = modifier,
        navigationIcon = { BackButton() },
        actions = {
            SoundBoardDelete()
        },
        colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = MaterialTheme.colorScheme.onPrimary)
    )
}

@Composable
fun SoundBoardDelete(
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = {},
    ) {
        Icon(
            Icons.Outlined.Delete,
            contentDescription = "Delete",
            tint = Color.Red,
            modifier = modifier.size(30.dp)
        )
    }
}

@Composable
fun SoundBoardBar(
    modifier: Modifier = Modifier
) {
    BottomAppBar(
        modifier = modifier.padding(0.dp),
        contentPadding = PaddingValues(0.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Button(
                onClick = { /* ... */ },
                shape = RectangleShape,
                modifier = modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .fillMaxWidth()
            ) {
                Icon(
                    Icons.Filled.Edit,
                    contentDescription = "Edit",
                    modifier = Modifier.size(35.dp)
                )
            }

            Button(
                onClick = { /* ... */ },
                shape = RectangleShape,
                modifier = modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .fillMaxWidth()
            ) {
                // Inner content including an icon and a text label
                Icon(
                    Icons.Filled.Add,
                    contentDescription = "Edit",
                    modifier = Modifier.size(40.dp)
                )
            }

        }
    }
}



@Preview
@Composable
fun SoundBoard(
    modifier: Modifier = Modifier
) {
    //val data = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
    val data = listOf(
        Audio(1),
        Audio(2),
        Audio(3),
        Audio(4),
        Audio(5),
        Audio(6),
        Audio(7),
        Audio(8),
        Audio(9),
        Audio(10),
        Audio(11),
        Audio(12)
    )
    Box(
        modifier = modifier
            .width(375.dp)
            .height(500.dp)
            .clip(RoundedCornerShape(25.dp))
            .shadow(
                elevation = 10.dp,
                spotColor = MaterialTheme.colorScheme.onSurfaceVariant,
                shape = RoundedCornerShape(25.dp)
            )
            .background(MaterialTheme.colorScheme.onSurfaceVariant),
        contentAlignment = Alignment.TopStart
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(8.dp),
            userScrollEnabled = false
        ) {
            /*items(items = data, key = {audio -> audio}) { audio ->
                AudioTile(audio = Audio(1))
            } */
            items(items = data) { audio ->
                AudioTile(audio = audio)
            }
        }
    }
}


@Composable
fun SoundBoardIcon() {
    /*
    Image(
        modifier = Modifier
            .size(64.dp)
            .padding(8.dp)
            .clip(RectangleShape),
        contentScale = ContentScale.Crop,
        painter = painterResource(),
        contentDescription = null
    )
     */
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AudioTile(
    modifier: Modifier = Modifier,
    audio: Audio
) {
    Card(
        onClick = {},
        shape = RoundedCornerShape(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (!audio.empty) MaterialTheme.colorScheme.surfaceVariant else Color.Transparent,
        ),
        modifier = modifier
            .width(120.dp)
            .height((120).dp)
            .padding(4.dp),
        elevation = CardDefaults.cardElevation(7.dp),
        border = if (audio.empty) BorderStroke(5.dp, Color.Black) else null
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.fillMaxSize()

        ) {
            if (!audio.empty) {
                Text(
                    text = audio.audioName,
                    fontSize = 16.sp,
                    style = MaterialTheme.typography.labelMedium
                )
            }

        }

    }
}

@Composable
fun SoundBoardSelectedDot() {

}


@Preview
@Composable
fun SoundBoardPreview() {
    SoundBoardScreen()
}