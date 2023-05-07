package com.example.sinemo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class AudioViewModel: ViewModel() {

    var recordsState by mutableStateOf(
        listOf(
            DataRecord(
                heading = numRec.toString(),
                subtext = System.currentTimeMillis().toString(),
                audioPath = output
            )
        )
    )
        private set

    fun addRecord(record: DataRecord) {
        recordsState = recordsState + listOf(record)
    }
}


