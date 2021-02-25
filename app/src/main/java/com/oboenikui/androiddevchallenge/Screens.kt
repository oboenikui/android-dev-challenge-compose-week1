package com.oboenikui.androiddevchallenge

import android.os.Bundle
import androidx.navigation.NavType
import androidx.navigation.compose.navArgument

sealed class Screens(val route: String) {
    object List : Screens("dogs")

    object Detail : Screens("dogs/{dogId}") {
        val navArguments = listOf(navArgument("dogId") { type = NavType.IntType })

        fun routeOf(dogId: Int) = "dogs/${dogId}"

        fun parseArguments(arguments: Bundle) =
            Arguments(arguments.getInt("dogId"))


        data class Arguments(val dogId: Int)
    }
}