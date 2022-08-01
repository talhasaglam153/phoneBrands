package com.tcoding.phonebrands.model

import java.io.Serializable

data class DataX(
    val current_page: Int,
    val last_page: Int,
    val phones: ArrayList<PhoneX>,
    val title: String
):Serializable