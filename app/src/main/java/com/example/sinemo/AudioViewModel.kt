package com.example.sinemo

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import java.util.Date

class AudioViewModel: ViewModel() {
    private val currentTimeMillis = System.currentTimeMillis()
    private val date = Date(currentTimeMillis)
    @SuppressLint("SimpleDateFormat")
    private val dateFormat = SimpleDateFormat("HH:mm:ss dd/MM/yyyy ")
    private val formattedDate = dateFormat.format(date)

    var recordsState = mutableStateListOf(
        DataRecord(
            heading = "recording$numRec",
            subtext = formattedDate.toString(),
            audioPath = output
        )
    )
        private set

    fun addRecord(record: DataRecord) {
        recordsState.add(record)
    }
}
