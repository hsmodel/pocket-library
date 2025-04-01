package com.hsmodel.rightnow.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star

object NavBarItems {
    val barItems = listOf(
        BarItem(
            name = "Home",
            image = Icons.Filled.Home,
            route = NavRoutes.Home.route
        ),
        BarItem(
            name = "Group",
            image = Icons.Filled.Star,
            route = NavRoutes.Group.route
        )
    )
}