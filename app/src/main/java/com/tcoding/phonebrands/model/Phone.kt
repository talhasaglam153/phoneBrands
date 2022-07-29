package com.tcoding.phonebrands.model

import java.io.Serializable

data class Phone(
    val `data`: List<Data>,
    val status: Boolean
): Serializable