package com.example.sinemo

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Handler
import android.os.Message
import android.util.Log

var activeThread = false
var currentAmplitude = 0
var previousValue = 0
//Method run of the thread. It is sending a message to the handler every 2 sec.
fun run() {
    try {
        activeThread = true
        while (activeThread) {
            Log.i(TAG, "onRun()")
            Thread.sleep(2000)
            previousValue = mediaRecorder!!.maxAmplitude
            threadHandler.sendEmptyMessage(0)
        }
    } catch (e: InterruptedException) {
        // TODO Auto-generated catch block
        e.printStackTrace()
    }
}


/***
 * Handler receives the message from the thread, and modify the UIThread as needed
 * Focused on the detection of the amplitude
 */
val threadHandler: Handler = @SuppressLint("HandlerLeak")
object : Handler() {
    override fun handleMessage(msg: Message) {
        Log.i(TAG, "handleMessage")
        //val previousValue: Int = currentAmplitude
        Log.i(TAG, "handleMessage : previous value : " + Integer.toString(currentAmplitude))
        currentAmplitude = mediaRecorder!!.maxAmplitude
        Log.i(TAG, "handleMessage : MaxAmplitude : " + Integer.toString(currentAmplitude))
        //if (previousValue < AMPLITUDE_END && currentAmplitude < AMPLITUDE_END && mediaRecorder != null) {
        if (previousValue > currentAmplitude && mediaRecorder != null) {
            Log.i(TAG, "handleMessage under 300")
            stopRecording()
        }
    }
}
