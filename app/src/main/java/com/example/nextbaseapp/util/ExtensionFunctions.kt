package com.example.nextbaseapp.util

import android.content.Context
import android.util.Log
import android.widget.Toast
import kotlinx.coroutines.delay
import retrofit2.HttpException
import java.io.IOException

fun Context.showToastMessage(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

suspend fun <T> retryIO(
    times: Int = Int.MAX_VALUE,
    initialDelay: Long = 100, // 0.1 second
    maxDelay: Long = 1000,    // 1 second
    factor: Double = 2.0,
    block: suspend () -> T): T
{
    var currentDelay = initialDelay
    repeat(times - 1) {
        Log.d("Retrying",(times-1).toString())
        try {
            return block()
        } catch (e: IOException) {
        } catch(n : NullPointerException ){
        } catch(d :Exception){}
        catch(h : HttpException)
        {
            // you can log an error here and/or make a more finer-grained
            // analysis of the cause to see if retry is needed
        }
        delay(currentDelay)
        currentDelay = (currentDelay * factor).toLong().coerceAtMost(maxDelay)
    }
    return block() // last attempt
}
