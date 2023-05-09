@file:Suppress("DEPRECATION")

package com.example.sinemo

import android.annotation.SuppressLint
import android.media.MediaRecorder
import android.os.Build
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.annotation.RequiresApi
import java.io.IOException
import kotlin.math.log10

var output: String = ""
//var output: String? = null
var mediaRecorder: MediaRecorder? = null
var state: Boolean = false
var maxAmplitude = 0
var lastMaxAmplitude = 0
var lastMaxAmplitudeTime = 0L
var numRec = 0

val handler = Handler(Looper.getMainLooper())
private val amplitudeRunnable = object : Runnable {
    override fun run() {
        if (mediaRecorder != null) {
            maxAmplitude = mediaRecorder!!.maxAmplitude
            val db = 20 * log10(maxAmplitude / 1.0)
            val dbLast = 20 * log10(lastMaxAmplitude / 1.0)
            Log.d("AMPLITUDE", "Max amplitude: $db dB")
            val currentTime = System.currentTimeMillis()
            if (dbLast - db > 20 || currentTime - lastMaxAmplitudeTime >= 10000) {
                stopRecording(audioViewModel = AudioViewModel())
            } else {
                lastMaxAmplitude = maxAmplitude
                handler.postDelayed(this, 3000L)
            }
        }
    }
}
@RequiresApi(Build.VERSION_CODES.Q)
fun startRecording() {
    try {
        numRec++
        output = Environment.getExternalStorageDirectory().absolutePath + "/recording$numRec.ogg"
        mediaRecorder = MediaRecorder()
        mediaRecorder?.setAudioSource(MediaRecorder.AudioSource.MIC)
        mediaRecorder?.setOutputFormat(MediaRecorder.OutputFormat.OGG)
        mediaRecorder?.setAudioEncoder(MediaRecorder.AudioEncoder.OPUS)
        mediaRecorder?.setAudioSamplingRate(44100)
        mediaRecorder?.setOutputFile(output)
        try {
            mediaRecorder?.prepare()
            mediaRecorder?.start()
            state = true
            lastMaxAmplitudeTime = System.currentTimeMillis()
            handler.postDelayed(amplitudeRunnable, 100L)
            Log.d("REC", state.toString())
        } catch (e: IllegalStateException) {
            e.printStackTrace()
            mediaRecorder = null
        } catch (e: IOException) {
            e.printStackTrace()
            mediaRecorder = null
        }
    } catch (e: RuntimeException) {
        e.printStackTrace()
    }
}
@SuppressLint("MissingPermission")
fun stopRecording(audioViewModel: AudioViewModel) {
    if (state) {
        try {
            mediaRecorder?.stop()
        } catch (stopException: RuntimeException) {
            stopException.printStackTrace()
        } finally {
            mediaRecorder?.reset()
            mediaRecorder?.release()
            mediaRecorder = null
        }
        state = false
        Log.d("REC", state.toString())
        handler.removeCallbacks(amplitudeRunnable)
        lastMaxAmplitude = 0
        lastMaxAmplitudeTime = 0L
        audioViewModel.addRecord(
            DataRecord(
                heading = "recording$numRec",
                subtext = "",
                audioPath = output
            )
        )
    }
}


