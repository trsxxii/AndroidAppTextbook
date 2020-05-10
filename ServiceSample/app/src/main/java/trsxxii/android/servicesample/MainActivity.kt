package trsxxii.android.servicesample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fromNotification = intent.getBooleanExtra("fromNotification", false)
        if (fromNotification) {
            val btPlay = findViewById<Button>(R.id.btPlay)
            btPlay.isEnabled = false
            val btStop = findViewById<Button>(R.id.btStop)
            btStop.isEnabled = true
        }
    }

    fun onPlayButtonClick(view: View) {
        val intent = Intent(applicationContext, SoundManageService::class.java)
        startService(intent)

        val btPlay = findViewById<Button>(R.id.btPlay)
        btPlay.isEnabled = false
        val btStop = findViewById<Button>(R.id.btStop)
        btStop.isEnabled = true
    }

    fun onStopButtonClick(view: View) {
        val intent = Intent(applicationContext, SoundManageService::class.java)
        stopService(intent)

        val btPlay = findViewById<Button>(R.id.btPlay)
        btPlay.isEnabled = true
        val btStop = findViewById<Button>(R.id.btStop)
        btStop.isEnabled = false
    }
}
