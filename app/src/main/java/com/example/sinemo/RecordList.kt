package com.example.sinemo

import androidx.compose.runtime.mutableStateListOf

class RecordList {
    private val records = mutableStateListOf<DataRecord>()

    fun addRecord(record: DataRecord) {
        records.add(record)
    }
    fun getRecordList(): List<DataRecord> {
        return records.toList()
    }
}