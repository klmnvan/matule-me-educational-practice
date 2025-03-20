package com.example.matuleme.presentation.screens.main.sidemenu

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.matuleme.R
import com.example.matuleme.presentation.components.dialogs.DialogError
import com.example.matuleme.presentation.components.spacers.SpacerHeight
import com.example.matuleme.presentation.components.spacers.SpacerWidth
import com.example.matuleme.presentation.navigation.NavigationRoutes
import com.example.matuleme.presentation.ui.theme.MatuleMeTheme
import com.example.matuleme.presentation.ui.theme.accent
import com.example.matuleme.presentation.ui.theme.background
import com.example.matuleme.presentation.ui.theme.block

@Composable
fun SideMenu(controller: NavHostController, vm: SideMenuViewModel = hiltViewModel()) {

    val state = vm.state.collectAsState()

    LaunchedEffect(Unit) {
        vm.getData()
    }

    if(state.value.dialogIsOpen) {
        DialogError("Ошибка", state.value.error) {
            vm.stateValue = state.value.copy(dialogIsOpen = false)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(accent)
            .padding(horizontal = 20.dp, vertical = 80.dp)
    ) {
        Column(modifier = Modifier.padding(start = 15.dp)) {
            Image(bitmap = ImageBitmap.imageResource(R.drawable.picture), contentDescription = "",
                modifier = Modifier.size(100.dp))
            SpacerHeight(14.dp)
            Text( "${state.value.profile.firstname} ${state.value.profile.lastname}",
                style = MatuleMeTheme.typography.titleCard.copy(color = block, fontSize = 20.sp))
            SpacerHeight(55.dp)
        }
        ButtonSideMenu(R.drawable.icon_profile, "Профиль") {
            controller.navigate(NavigationRoutes.BASICPROFILE)
        }
        SpacerHeight(30.dp)
        ButtonSideMenu(R.drawable.icon_shop, "Корзина") {
            controller.navigate(NavigationRoutes.BUCKET)
        }
        SpacerHeight(30.dp)
        ButtonSideMenu(R.drawable.icon_fav, "Избранное") {
            controller.navigate(NavigationRoutes.FAVOURITE)
        }
        SpacerHeight(30.dp)
        ButtonSideMenu(R.drawable.icon_order, "Заказы") {
        }
        SpacerHeight(30.dp)
        ButtonSideMenu(R.drawable.icon_notif, "Уведомления") {
            controller.navigate(NavigationRoutes.NOTIFICATION)
        }
        SpacerHeight(30.dp)
        ButtonSideMenu(R.drawable.icon_settings2, "Настройки") {

        }
        SpacerHeight(38.dp)
        Divider(thickness = 1.dp, modifier = Modifier.fillMaxWidth(), color = block)
        SpacerHeight(30.dp)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    vm.signOut(controller)
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(0.3f)) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.icon_log_out),
                    tint = block,
                    contentDescription = ""
                )
            }
            Text("Выйти", style = MatuleMeTheme.typography.titleCard.copy(color = block),
                modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.icon_arrow_size_menu),
                tint = block,
                contentDescription = "",
                modifier = Modifier.alpha(0f)
            )
            SpacerWidth(40.dp)
        }
        Spacer(modifier = Modifier.weight(1f))
    }

}

@Composable
fun ColumnScope.ButtonSideMenu(iconId: Int, title: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.weight(0.3f)) {
            Icon(
                imageVector = ImageVector.vectorResource(iconId),
                tint = block,
                contentDescription = ""
            )
        }
        Text(title, style = MatuleMeTheme.typography.titleCard.copy(color = block),
            modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.icon_arrow_size_menu),
            tint = block,
            contentDescription = ""
        )
        SpacerWidth(40.dp)
    }
}

