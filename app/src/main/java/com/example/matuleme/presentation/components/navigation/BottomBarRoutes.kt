package com.example.matuleme.presentation.components.navigation

import com.example.matuleme.R
import com.example.matuleme.presentation.navigation.NavigationRoutes

sealed class BottomBarRoutes(
    val route: String,
    val resourceId: Int? = null
) {
    object HomeScreen : BottomBarRoutes(
        route = NavigationRoutes.HOME,
        resourceId = R.drawable.icon_home
    )

    object FavScreen : BottomBarRoutes(
        route = NavigationRoutes.FAVOURITE,
        resourceId = R.drawable.icon_fav
    )

    object BucketScreen : BottomBarRoutes(
        route = NavigationRoutes.BUCKET,
        resourceId = R.drawable.icon_splash
    )

    object NotifScreen : BottomBarRoutes(
        route = NavigationRoutes.NOTIFICATION,
        resourceId = R.drawable.icon_notif
    )

    object ProfileScreen : BottomBarRoutes(
        route = NavigationRoutes.BASICPROFILE,
        resourceId = R.drawable.icon_profile
    )
}