package com.muthu.couroutinestest

import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO

class UserManager2 {

    var count = 0
    lateinit var defrredCount: Deferred<Int>

    suspend fun getTotalUserCount(): Int {

        coroutineScope {
            launch {
                delay(1000)
                count = 50
            }

            defrredCount = async(IO) {
                return@async 70
            }
        }

        return count + defrredCount.await()
    }
}