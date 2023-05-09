package com.example.sinemo

import androidx.lifecycle.ViewModel

class AudioViewModel(
    val recordList: MutableList<DataRecord>
) : ViewModel() {

    fun addRecord(record: DataRecord) {
        recordList.add(record)
    }
}
