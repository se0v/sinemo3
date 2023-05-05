package com.example.sinemo.ui.theme

import android.app.Application
import android.media.AudioRecord
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

var outputFile: String = ""
class AudioRecordViewModel(application: Application) : AndroidViewModel(application) {

    private val _audioRecords = MutableLiveData<List<AudioRecord>>()
    val audioRecords: LiveData<List<AudioRecord>>
        get() = _audioRecords

}