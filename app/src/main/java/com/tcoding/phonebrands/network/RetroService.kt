package com.tcoding.phonebrands.network

import com.tcoding.phonebrands.model.Phone
import com.tcoding.phonebrands.model.PhoneDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetroService {


    @GET("brands")
    fun getPhoneBrands(): Call<Phone>

    @GET("brands/{slug}")
    fun getPhone(@Path("slug") slug: String): Call<PhoneDetail>



}