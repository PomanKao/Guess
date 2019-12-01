package com.home.guess

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_record.*

class RecordActivity : AppCompatActivity() {

    private val TAG = RecordActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)

        val counter = intent.getIntExtra("COUNTER", -1)
        tv_counter.text = counter.toString()

        btn_save.setOnClickListener{ view ->
            val nickname = ed_nickname.text.toString()
            getSharedPreferences("guess", Context.MODE_PRIVATE)
                .edit()
                .putString("counter", counter.toString())
                .putString("nickname", nickname)
                .apply()
            setResult(Activity.RESULT_OK)
            finish()
        }

    }
}
