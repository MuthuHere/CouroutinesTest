package com.muthu.couroutinestest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button2.setOnClickListener {
            textView2.text = "${++count}"
        }

        button.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                download()
            }

        }
    }

    private fun download() {
        for (i in 1..100000) {
            Log.i("MMM ", "Downloading user $i in ${Thread.currentThread().name}")
        }
    }
}