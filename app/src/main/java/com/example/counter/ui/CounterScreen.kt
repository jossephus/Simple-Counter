package com.example.counter.ui

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CounterScreen(
    playViewModel: PlayViewModel = viewModel()
) {
    val uiState by playViewModel.uiState.collectAsState()
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(uiState.count.toString(), style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            Button(onClick = {
                playViewModel.increaseCount()
            }) {
                Text("+")
            }
            Button(onClick = {
                playViewModel.decreaseCount()
            }) {
                Text("-")
            }
        }
        Text("Your Input: `${uiState.msg}`")
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = playViewModel.msg,
            onValueChange = {
                playViewModel.updateMsg(it)
            }
        )
    }

    if (uiState.done) {
        val activity = (LocalContext.current as Activity)
        AlertDialog(
            onDismissRequest = {

            },
            title = { Text("Done") },
            text = { Text("Count Finished") },
            dismissButton = {
                TextButton(onClick = {
                    activity.finish()
                }) {
                    Text("exit")
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    playViewModel.reset()
                }) {
                    Text("play again")
                }
            },
        )
    }
}