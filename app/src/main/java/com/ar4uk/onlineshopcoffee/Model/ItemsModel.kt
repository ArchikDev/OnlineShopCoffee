package com.ar4uk.onlineshopcoffee.Model

data class ItemsModel(
    val title: String = "",
    val description: String = "",
    val picUrl: List<String> = listOf(),
    val price: Double = 0.0,
    val rating: Double = 0.0,
    val numberInCart: Int = 0,
    val extra: String = "",
    val id: Int = 0,
)
