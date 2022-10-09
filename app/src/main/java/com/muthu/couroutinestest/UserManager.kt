package com.muthu.couroutinestest

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class UserManager {

    suspend fun getTotalUserCount(): Int {
        var count = 0
        CoroutineScope(IO).launch {
            delay(1000)
            count = 50
        }

        val data = CoroutineScope(IO).async {
            delay(3000)
            return@async 80
        }

        return count + data.await()
    }
}