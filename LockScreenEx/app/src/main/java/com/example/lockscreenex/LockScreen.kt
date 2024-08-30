package com.example.lockscreenex

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun LockScreen(modifier: Modifier = Modifier){
    Column {
        Text(text = LocalDateTime.now().toString())
    }
}