package com.tcoding.phonebrands.network

import com.tcoding.phonebrands.model.Phone
import retrofit2.Call
import retrofit2.http.GET

interface RetroService {


    @GET("brands")
    fun getPhoneBrands(): Call<Phone>
    

}