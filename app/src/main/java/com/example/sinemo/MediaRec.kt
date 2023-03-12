package com.example.sinemo

import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaRecorder
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.IOException
import java.util.jar.Manifest

var output: String? = null
var mediaRecorder: MediaRecorder? = null
var state: Boolean = false

fun startRecording() {
    try {
        output = Environment.getExternalStorageDirectory().absolutePath + "/recording.ogg"
        mediaRecorder = MediaRecorder()

        mediaRecorder?.setAudioSource(MediaRecorder.AudioSource.MIC)
        mediaRecorder?.setOutputFormat(MediaRecorder.OutputFormat.OGG)
        mediaRecorder?.setAudioEncoder(MediaRecorder.AudioEncoder.OPUS)
        mediaRecorder?.setOutputFile(output)
        try {
            mediaRecorder?.prepare()
            mediaRecorder?.start()
            state = true
            run()
            Log.d("STREC", state.toString())
        } catch (e: IllegalStateException) {
            e.printStackTrace()
            mediaRecorder = null
        } catch (e: IOException) {
            e.printStackTrace()
            mediaRecorder = null
        }
    }catch (e: RuntimeException) {
        e.printStackTrace()
    }
}

fun stopRecording(){
    if(state) {
            try {
                mediaRecorder?.stop()
            } catch (stopException: RuntimeException){}
            finally {
                mediaRecorder?.reset()
                mediaRecorder?.release()
            }
                state = false
                Log.d("STREC", state.toString())
                mediaRecorder = null
            }
    activeThread = false
    currentAmplitude = 0
}