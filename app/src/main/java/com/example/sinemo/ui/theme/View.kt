package com.example.sinemo.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState

@Composable
fun AudioRecordList(audioRecordViewModel: AudioRecordViewModel) {
    val audioRecords by audioRecordViewModel.audioRecords.observeAsState(emptyList())

    LazyColumn {
        items(audioRecords) { audioRecord ->
            Text(text = outputFile)
        }
    }
}

@Composable
fun AudioRecordScreen(audioRecordViewModel: AudioRecordViewModel) {
        AudioRecordList(audioRecordViewModel)
    }
