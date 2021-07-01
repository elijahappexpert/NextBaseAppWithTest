package com.example.nextbaseapp.Mvvm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nextbaseapp.Constants.Constants.RESPONSE_TAG
import com.example.nextbaseapp.model.Journey_model
import com.example.nextbaseapp.repo.Repository
import com.example.nextbaseapp.util.coroutine
import com.example.nextbaseapp.util.retryIO
import kotlinx.coroutines.delay
import retrofit2.HttpException
import java.io.IOException

class DataViewModel (val repository: Repository): ViewModel() {

    var ResponseList = MutableLiveData<Journey_model>()

    init {
        refreshNetworkCall()
    }

    fun refreshNetworkCall() {

        coroutine {

            var response = retryIO(times = 10) { repository.getData()}
            if (response.isSuccessful) {
                response.body()?.let {

                    ResponseList.postValue(response.body())

                }
            } else {

                retryIO(times = 10) {response = repository.getData() }
                //we can just handle the network error somehow here, like
                Log.e(RESPONSE_TAG, response.errorBody().toString())
            }

        }
    }



}