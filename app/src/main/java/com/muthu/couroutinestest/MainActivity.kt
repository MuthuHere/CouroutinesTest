package com.muthu.couroutinestest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

        //challenge
        CoroutineScope(Dispatchers.Main).launch {
            Log.i("MMM a", Thread.currentThread().name)
        }
        CoroutineScope(Dispatchers.IO).launch {
            Log.i("MMM b", Thread.currentThread().name)
        }
    }

    /**
     * using suspend function
     * to use withContext(which is used to change thread)
     * in IO thread we cannot use view
     * so changing IO thread to Main thread
     * for that we are using withContext function also if we need to use withContext
     * we must declare the function is suspend
     */
    private suspend fun download() {
        for (i in 1..100000) {
            withContext(Dispatchers.Main) {
                textView.text = "Downloading user $i in ${Thread.currentThread().name}"
            }
        }
    }
}