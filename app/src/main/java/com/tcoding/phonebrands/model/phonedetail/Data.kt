package com.tcoding.phonebrands.model.phonedetail

data class Data(
    val brand: String,
    val dimension: String,
    val os: String,
    val phone_images: List<String>,
    val phone_name: String,
    val release_date: String,
    val specifications: List<Specification>,
    val storage: String,
    val thumbnail: String
)