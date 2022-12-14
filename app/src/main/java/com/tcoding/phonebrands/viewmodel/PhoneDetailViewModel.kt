package com.tcoding.phonebrands.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tcoding.phonebrands.model.PhoneDetail
import com.tcoding.phonebrands.network.RetroInstance
import com.tcoding.phonebrands.network.RetroService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

class PhoneDetailViewModel: ViewModel() {

    lateinit var phoneDetailLiveData: MutableLiveData<PhoneDetail>
    lateinit var dataLoad: MutableLiveData<Boolean>

    init {
        phoneDetailLiveData = MutableLiveData<PhoneDetail>()
        dataLoad = MutableLiveData<Boolean>()
        dataLoad.value = false
    }

    fun getLiveDataPhoneDetail(): MutableLiveData<PhoneDetail> {
        return phoneDetailLiveData
    }

    fun clearData() {
        dataLoad.value = false
        phoneDetailLiveData = MutableLiveData()
    }

    fun getLiveDataBoolean(): MutableLiveData<Boolean> {
        return dataLoad
    }

    fun callAPI(slug: String) {

        GlobalScope.launch(Dispatchers.IO) {
            val service = RetroInstance.getRetroInstance().create(RetroService::class.java)
            val response = service.getPhone(slug).awaitResponse()

            if(response.isSuccessful) {
                val data = response.body()!!

                withContext(Dispatchers.Main) {
                    phoneDetailLiveData.postValue(data)
                    dataLoad.value = true
                }

            }else {
                dataLoad.value = false
            }

        }

    }


}