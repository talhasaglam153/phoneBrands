package com.tcoding.phonebrands.model

import java.io.Serializable

data class Data(
    val brand_id: Int,
    val brand_name: String,
    val brand_slug: String,
    val detail: String,
    val device_count: Int
): Serializable