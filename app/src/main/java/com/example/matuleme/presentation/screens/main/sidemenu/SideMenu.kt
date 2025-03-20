package com.example.matuleme.presentation.screens.main.sidemenu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.matuleme.R
import com.example.matuleme.presentation.components.dialogs.DialogError
import com.example.matuleme.presentation.components.spacers.SpacerHeight
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
        ButtonSideMenu(R.drawable.icon_profile, "Профиль") {

        }
        SpacerHeight(30.dp)
        ButtonSideMenu(R.drawable.icon_profile, "Корзина") {

        }
        SpacerHeight(30.dp)
        ButtonSideMenu(R.drawable.icon_profile, "Избранное") {

        }
        SpacerHeight(30.dp)
        ButtonSideMenu(R.drawable.icon_profile, "Заказы") {

        }
        SpacerHeight(30.dp)
        ButtonSideMenu(R.drawable.icon_profile, "Уведомления") {

        }
        SpacerHeight(30.dp)
        ButtonSideMenu(R.drawable.icon_profile, "Настройки") {

        }
        SpacerHeight(38.dp)
        Divider(thickness = 1.dp, modifier = Modifier.fillMaxWidth(), color = block)
        SpacerHeight(30.dp)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.icon_shop),
                tint = block,
                contentDescription = "",
                modifier = Modifier.weight(0.2f)
            )
            Text("Выйти", modifier = Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.weight(1f))
    }

}

@Composable
fun ColumnScope.ButtonSideMenu(iconId: Int, title: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(iconId),
            tint = block,
            contentDescription = ""
        )
    }
}

