package com.tcoding.phonebrands.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tcoding.phonebrands.model.phonedetail.Feature
import com.tcoding.phonebrands.network.RetroInstance
import com.tcoding.phonebrands.network.RetroService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

class PhoneFeatureViewModel: ViewModel() {

    lateinit var phoneFeaturesLiveData: MutableLiveData<Feature>
    lateinit var dataLoad: MutableLiveData<Boolean>

    init {
        phoneFeaturesLiveData = MutableLiveData<Feature>()
        dataLoad = MutableLiveData<Boolean>()
        dataLoad.value = false
    }


    fun getLiveDataList(): MutableLiveData<Feature> {
        return phoneFeaturesLiveData
    }

    fun getLiveDataBoolean(): MutableLiveData<Boolean> {
        return dataLoad
    }

    fun clearData() {
        dataLoad.value = false
        phoneFeaturesLiveData = MutableLiveData()
    }

    fun callAPI(slug: String) {
        GlobalScope.launch(Dispatchers.IO) {

            val service = RetroInstance.getRetroInstance().create(RetroService::class.java)

            val response = service.getPhoneFeature(slug).awaitResponse()

            if(response.isSuccessful) {
                val data = response.body()!!

                withContext(Dispatchers.Main) {
                    phoneFeaturesLiveData.postValue(data)
                    dataLoad.value = true
                }

            }else {
                dataLoad.value = false
            }

        }
    }



}