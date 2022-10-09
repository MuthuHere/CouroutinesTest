package com.muthu.couroutinestest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

class MainActivity : AppCompatActivity() {

    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button2.setOnClickListener {
            textView2.text = "${++count}"
        }
        button.setOnClickListener {
            CoroutineScope(Main).launch {
                textView.text = UserManager().getTotalUserCount().toString()
//                download()
            }
        }

    }


    private suspend fun download() {
        for (i in 1..100000) {
            withContext(Dispatchers.Main) {
                textView.text = "Downloading user $i in ${Thread.currentThread().name}"
            }
        }
    }
}


