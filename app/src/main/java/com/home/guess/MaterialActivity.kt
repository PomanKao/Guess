package com.home.guess

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_material.*
import kotlinx.android.synthetic.main.content_material.*

class MaterialActivity : AppCompatActivity() {

    private val TAG = MaterialActivity::class.java.simpleName

    val secretNumber = SecretNumber()
    private val REQUEST_RECORD = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material)
        setSupportActionBar(toolbar)

        Log.d(TAG, "secret: ${secretNumber.secret}")

        val preferences = getSharedPreferences("guess", Context.MODE_PRIVATE)
        val counterStr = preferences.getString("counter", null)
        val nickname = preferences.getString("nickname", null)
        Log.d(TAG, "counter= $counterStr, nickname= $nickname")

        fab.setOnClickListener { view ->
            replay()
        }

        tv_count.text = secretNumber.count.toString()
    }

    private fun replay() {
        AlertDialog.Builder(this)
            .setTitle("Replay game")
            .setMessage("Are you sure?")
            .setPositiveButton(getString(android.R.string.ok)) { dialog, which ->
                secretNumber.reset()
                tv_count.text = secretNumber.count.toString()
                ed_number.setText("")
            }
            .setNeutralButton(getString(android.R.string.cancel), null)
            .show()
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

        tv_count.text = secretNumber.count.toString()

//        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.message))
            .setMessage(message)
            .setPositiveButton(android.R.string.ok) { dialog, which ->
                if (diff == 0) {
                    val intent = Intent(this, RecordActivity::class.java)
                    intent.putExtra("COUNTER", secretNumber.count)
                    startActivityForResult(intent, REQUEST_RECORD)
                }
            }
            .show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_RECORD) {
            if (resultCode == Activity.RESULT_OK) {
                val nickname = data?.getStringExtra("NICKNAME")
                Log.d(TAG, "nickname is $nickname")
                replay()
            }
        }
    }
}
