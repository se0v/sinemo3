package com.example.sinemo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class AudioRecordViewModel(application: Application) : AndroidViewModel(application) {
    private val _audioRecords = MutableLiveData<List<AudioRecord>>()
    val audioRecords: LiveData<List<AudioRecord>>
        get() = _audioRecords
}