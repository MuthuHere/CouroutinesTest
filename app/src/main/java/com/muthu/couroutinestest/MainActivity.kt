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


        //test 1

        //series  call
        /*   CoroutineScope(IO).launch {
               Log.i("MMM ", "Started---")
               val stock1 = getStock1()
               val stock2 = getStock2()
               val totalStock = stock1 + stock2
               Log.i("MMM ", "total stock $totalStock")
           }*/

        //parallel call with async
        CoroutineScope(IO).launch {
            Log.i("MMMM ", "Started---")
            val stock1 = async { getStock1() }
            val stock2 = async { getStock2() }
            val totalStock = stock1.await() + stock2.await()

            Log.i("MMMM ", "total stock $totalStock")
        }

        // same parallel function with Main thread
        CoroutineScope(Main).launch {
            Log.i("MMMM ", "Started---")
            val stock1 = async(IO) { getStock1() }
            val stock2 = async(IO) { getStock2() }
            val totalStock = stock1.await() + stock2.await()
            Toast.makeText(applicationContext, "total stock $totalStock", Toast.LENGTH_SHORT).show()
            Log.i("MMMM ", "total stock $totalStock")
        }
    }

    private suspend fun getStock1(): Int {
        delay(10000)
        Log.i("MMM ", "Stock 1 returned")
        return 45000
    }

    private suspend fun getStock2(): Int {
        delay(8000)
        Log.i("MMM ", "Stock 2 returned")
        return 45000
    }

    /**
     * using suspend function
     * to use withContext(which is used to change thread)
     * in IO thread we cannot use view
     * so changing IO thread to Main thread
     * for that we are using withContext function also if we need to use withContext
     * we must declare the function is suspend
     * similarly suspend functions have below
     * 1. withContext 2. withTimeout 3. withTimeoutOrNull
     * 4. join  5. delay 6. await 7 supervisorScope
     * 8. coroutineScope
     */
    private suspend fun download() {
        for (i in 1..100000) {
            withContext(Dispatchers.Main) {
                textView.text = "Downloading user $i in ${Thread.currentThread().name}"
            }
        }
    }
}


