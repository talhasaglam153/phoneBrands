package com.tcoding.phonebrands.model

import java.io.Serializable

data class Phone(
    val `data`: ArrayList<Data>,
    val status: Boolean
): Serializable