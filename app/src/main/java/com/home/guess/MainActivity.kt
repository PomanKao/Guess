package com.home.guess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    val secretNumber = SecretNumber()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "secret number: ${secretNumber.secret}")
    }


    fun check(view: View) {
        val n = ed_number.text.toString().toInt()
        Log.d(TAG, "number: $n")
        val diff = secretNumber.validate(n)
        var message = getString(R.string.yes_you_got_it)
        if (diff < 0) {
            message = getString(R.string.bigger)
        } else if (diff > 0) {
            message = getString(R.string.smaller)
        }
//        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.message))
            .setMessage(message)
            .setPositiveButton(android.R.string.ok, null)
            .show()
    }
}
