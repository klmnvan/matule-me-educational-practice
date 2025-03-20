package com.example.matuleme.presentation.components.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.matuleme.presentation.components.spacers.SpacerHeight
import com.example.matuleme.presentation.navigation.NavigationRoutes
import com.example.matuleme.presentation.ui.theme.MatuleMeTheme
import com.example.matuleme.presentation.ui.theme.accent
import com.example.matuleme.presentation.ui.theme.block
import com.example.matuleme.presentation.ui.theme.subtextdark

@Composable
fun BottomBar(controller: NavHostController) {
    val screens = listOf(BottomBarRoutes.HomeScreen, BottomBarRoutes.FavScreen, BottomBarRoutes.BucketScreen, BottomBarRoutes.NotifScreen, BottomBarRoutes.ProfileScreen)
    Box {
        val navBackStackEntry by controller.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        Row(
            modifier = Modifier
                .background(block)
                .padding(bottom = 30.dp, top = 16.dp)
                .height(IntrinsicSize.Max)) {
            screens.forEach { screen ->
                Column(
                    modifier = Modifier
                        .weight(1f).fillMaxHeight()
                        .clickable(interactionSource = remember { MutableInteractionSource() }, indication = null) {
                            if(currentRoute != screen.route) {
                                controller.navigate(screen.route) {
                                    currentRoute?.let {
                                        popUpTo(it) {
                                            inclusive = true
                                        }
                                    }
                                }
                            }
                        },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    var selectedColor = subtextdark
                    if(currentRoute == screen.route) {
                        selectedColor = accent
                    }
                    if(screen.route == NavigationRoutes.BUCKET) {
                        Column (
                            modifier = Modifier.clip(CircleShape).background(accent),
                        ) {
                            Spacer(modifier = Modifier.weight(1f))
                            Icon(imageVector = ImageVector.vectorResource(id = screen.resourceId!!),
                                modifier = Modifier.padding(16.dp).size(25.dp),
                                contentDescription = "", tint = block
                            )
                        }
                    }
                    else {
                        Icon(imageVector = ImageVector.vectorResource(id = screen.resourceId!!),
                            modifier = Modifier.size(25.dp),
                            contentDescription = "", tint = selectedColor
                        )
                    }
                }
            }
        }
    }
}