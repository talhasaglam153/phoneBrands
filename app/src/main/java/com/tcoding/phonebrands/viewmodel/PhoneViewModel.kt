package com.tcoding.phonebrands.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tcoding.phonebrands.model.Phone
import com.tcoding.phonebrands.network.RetroInstance
import com.tcoding.phonebrands.network.RetroService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

class PhoneViewModel: ViewModel() {

    var phoneBrandsLiveData: MutableLiveData<Phone>

    init {
        phoneBrandsLiveData = MutableLiveData<Phone>()
    }

    fun getLiveDataList(): MutableLiveData<Phone> {
        return phoneBrandsLiveData
    }

    fun callAPI() {
        GlobalScope.launch(Dispatchers.IO) {
            var service = RetroInstance.getRetroInstance().create(RetroService::class.java)
            var response = service.getPhoneBrands().awaitResponse()

            if(response.isSuccessful) {
                var data = response.body()!!

                withContext(Dispatchers.Main) {
                    phoneBrandsLiveData.postValue(data)
                }
            }
        }
    }


}