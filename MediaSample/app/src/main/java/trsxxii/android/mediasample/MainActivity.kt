package trsxxii.android.mediasample

import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CompoundButton
import android.widget.Switch
import java.io.IOException
import java.net.URI

class MainActivity : AppCompatActivity() {

    private var _player: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        _player = MediaPlayer()
        val mediaFileUriStr = "android.resource://${packageName}/${R.raw.cross_road}"
        val mediaFileUri = Uri.parse(mediaFileUriStr)
        try {
            _player?.setDataSource(applicationContext, mediaFileUri)
            _player?.setOnPreparedListener(PlayerPreparedListener())
            _player?.setOnCompletionListener(PlayerCompletionListener())
            _player?.prepareAsync()
        }
        catch (ex: IllegalArgumentException) {
            Log.e("MediaSample", "メディアプレーヤー準備時の例外発生", ex)
        }
        catch (ex: IOException) {
            Log.e("MediaSample", "メディアプレーヤー準備時の例外発生", ex)
        }

        val loopSwitch = findViewById<Switch>(R.id.swLoop)
        loopSwitch.setOnCheckedChangeListener(LoopSwitchChangedListener())
    }

    override fun onDestroy() {
        super.onDestroy()

        _player?.let {
            if (it.isPlaying) {
                it.stop()
            }
            it.release()
            _player = null
        }
    }

    fun onBackButtonClick(view: View) {
        _player?.seekTo(0)
    }

    fun onPlayButtonClick(view: View) {
        _player?.let {
            val btPlay = findViewById<Button>(R.id.btPlay)
            if (it.isPlaying) {
                it.pause()
                btPlay.setText(R.string.bt_play_play)
            } else {
                it.start()
                btPlay.setText(R.string.bt_play_pause)
            }
        }
    }

    fun onForwardButtonClick(view: View) {
        _player?.let {
            val duration = it.duration
            it.seekTo(duration)

            if (!it.isPlaying) {
                it.start()
            }
        }
    }

    private inner class PlayerPreparedListener : MediaPlayer.OnPreparedListener {
        override fun onPrepared(mp: MediaPlayer?) {
            val btPlay = findViewById<Button>(R.id.btPlay)
            btPlay.isEnabled = true
            val btBack = findViewById<Button>(R.id.btBack)
            btBack.isEnabled = true
            val btForward = findViewById<Button>(R.id.btForward)
            btForward.isEnabled = true
        }
    }

    private inner class PlayerCompletionListener : MediaPlayer.OnCompletionListener {
        override fun onCompletion(mp: MediaPlayer?) {
            _player?.let {
                if (!it.isLooping) {
                    val btPlay = findViewById<Button>(R.id.btPlay)
                    btPlay.setText(R.string.bt_play_play)
                }
            }
        }
    }

    private inner class LoopSwitchChangedListener : CompoundButton.OnCheckedChangeListener {
        override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
            _player?.isLooping = true
        }
    }
}
