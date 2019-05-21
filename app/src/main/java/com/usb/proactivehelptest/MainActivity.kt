package com.usb.proactivehelptest

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast

class MainActivity : AppCompatActivity() {


    val proactiveHelpHandler = Handler()
    var proactiveRunnable:Runnable? = null
    val PROACT_DURATION:Long = 5 * 1000
    var lastInteractionTime = System.currentTimeMillis()
    val TAG = MainActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lastInteractionTime = System.currentTimeMillis()
    }

    override fun onUserInteraction() {
        super.onUserInteraction()
        lastInteractionTime = System.currentTimeMillis()
    }


    override fun onResume() {
        super.onResume()
        proactiveRunnable = Runnable {
            if(System.currentTimeMillis() - lastInteractionTime >= PROACT_DURATION) {
                Log.d(TAG, "startproact help")
                Toast.makeText(this, "Starting Proact Help", Toast.LENGTH_LONG).show()
            } else {
                proactiveHelpHandler.removeCallbacks(proactiveRunnable)
                proactiveHelpHandler.postDelayed(proactiveRunnable,PROACT_DURATION)
            }
        }
        proactiveHelpHandler.postDelayed(proactiveRunnable,PROACT_DURATION)
    }

    override fun onPause() {
        super.onPause()
        proactiveHelpHandler.removeCallbacks(proactiveRunnable)
    }
}
