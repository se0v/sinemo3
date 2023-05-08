package com.example.sinemo

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.util.Date

class AudioViewModel: ViewModel() {
    private val currentTimeMillis = System.currentTimeMillis()
    private val date = Date(currentTimeMillis)
    @SuppressLint("SimpleDateFormat")
    private val dateFormat = SimpleDateFormat("HH:mm:ss dd/MM/yyyy ")
    private val formattedDate = dateFormat.format(date)

    var recordsState by mutableStateOf(
        listOf(
            DataRecord(
                heading = "recording$numRec",
                subtext = formattedDate.toString(),
                audioPath = output
            )
        )
    )
        private set

    fun addRecord(record: DataRecord) {
        recordsState = recordsState + listOf(record)
    }
}


