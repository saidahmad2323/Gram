package com.example.gramm

data class Tariff(
    val title: String,
    val icon: Int,
    val price: String
)
data class CardDetailInfo(
    val title: String,
    val icon: Int,
    val onClick: () -> Unit
)