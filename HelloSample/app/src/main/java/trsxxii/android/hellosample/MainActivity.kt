package trsxxii.android.hellosample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btClick = findViewById<Button>(R.id.btClick)
        val listener = HelloListener()
        btClick.setOnClickListener(listener)

        val btClear = findViewById<Button>(R.id.btClear)
        btClear.setOnClickListener(listener)

    }

    private inner class HelloListener : View.OnClickListener {
        override fun onClick(v: View?) {
            val input = findViewById<EditText>(R.id.etName)
            val output = findViewById<TextView>(R.id.tvOutput)

            when (v?.id) {
                R.id.btClick -> {
                    val inputStr = input.text.toString()
                    output.text = inputStr + "さん、こんにちわ！"
                }

                R.id.btClear -> {
                    input.setText("")
                    output.text = ""
                }
            }
        }
    }
}
