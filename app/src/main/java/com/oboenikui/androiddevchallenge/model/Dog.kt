package com.oboenikui.androiddevchallenge.model

import androidx.annotation.DrawableRes

data class Dog(
    val id: Int,
    val ageByMonth: Int?,
    val breed: String,
    val sex: Sex,
    val detail: String? = null,
    @DrawableRes val image: Int,
)

enum class Sex {
    Male, Female
}