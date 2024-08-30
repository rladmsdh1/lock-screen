package com.example.lockscreenex

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun LockScreen(modifier: Modifier = Modifier) {
    var result: List<Post> by remember {
        mutableStateOf(emptyList())
    }
    LaunchedEffect(Unit) {
        result = Ktor.getResponse()
    }
    if (result.isNotEmpty()) {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(4.dp),
            horizontalAlignment = CenterHorizontally,
        ) {
            items(result.subList(fromIndex = 0, toIndex = 5)) {
                Text(text = it.title)
            }
        }
    }
}