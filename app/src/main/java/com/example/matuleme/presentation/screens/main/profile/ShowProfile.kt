package com.example.matuleme.presentation.screens.main.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.matuleme.R
import com.example.matuleme.presentation.screens.main.profile.components.ButtonMedium
import com.example.matuleme.presentation.components.dialogs.DialogError
import com.example.matuleme.presentation.components.spacers.SpacerHeight
import com.example.matuleme.presentation.components.spacers.SpacerWidth
import com.example.matuleme.presentation.navigation.NavigationRoutes
import com.example.matuleme.presentation.screens.main.profile.components.EditProfileField
import com.example.matuleme.presentation.screens.main.profile.components.ProfileStates
import com.example.matuleme.presentation.screens.main.profile.components.ShowProfileField
import com.example.matuleme.presentation.ui.theme.MatuleMeTheme
import com.example.matuleme.presentation.ui.theme.accent
import com.example.matuleme.presentation.ui.theme.block
import com.example.matuleme.presentation.ui.theme.raleway

@Composable
fun ShowProfile(controller: NavHostController, vm: ShowProfileViewModel = hiltViewModel()) {

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
            .background(block)
            .padding(horizontal = 20.dp, vertical = 60.dp)
    ) {
        when (state.value.stateScreen) {
            ProfileStates.Show -> {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Icon(
                        painter = painterResource(R.drawable.icon_menu),
                        contentDescription = "",
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ) {
                                controller.navigate(NavigationRoutes.SIDEMENU)
                            },
                        tint = Color.Unspecified
                    )
                    SpacerWidth(12.dp)
                    Text("Профиль",
                        fontSize = 16.sp,
                        fontFamily = raleway,
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 20.sp,
                        modifier = Modifier.align(Alignment.Center))
                    SpacerWidth(12.dp)
                    Box (
                        modifier = Modifier.clip(CircleShape).background(accent).align(Alignment.CenterEnd),
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.icon_pencil),
                            modifier = Modifier.padding(8.dp).size(8.dp).clickable {
                                vm.stateValue = state.value.copy(stateScreen = ProfileStates.Edit)
                            },
                            contentDescription = "", tint = block
                        )
                    }
                }
                SpacerHeight(48.dp)
                Image(bitmap = ImageBitmap.imageResource(R.drawable.picture), contentDescription = "",
                    modifier = Modifier.size(96.dp).align(Alignment.CenterHorizontally))
                SpacerHeight(8.dp)
                Text(
                    "${state.value.profile.firstname} ${state.value.profile.lastname}",
                    style = MatuleMeTheme.typography.titleProfile,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                SpacerHeight(35.dp)
                //бар код
                SpacerHeight(20.dp)
                ShowProfileField("Имя", state.value.profile.firstname)
                SpacerHeight(17.dp)
                ShowProfileField("Фамилия", state.value.profile.lastname)
                SpacerHeight(17.dp)
                ShowProfileField("Адрес", state.value.profile.address)
                SpacerHeight(17.dp)
                ShowProfileField("Телефон", state.value.profile.phone)
                SpacerHeight(15.dp)
            }
            ProfileStates.Edit -> {
                ButtonMedium("Сохранить") {
                    vm.save()
                }
                SpacerHeight(50.dp)
                Image(bitmap = ImageBitmap.imageResource(R.drawable.picture), contentDescription = "",
                    modifier = Modifier.size(96.dp).align(Alignment.CenterHorizontally))
                SpacerHeight(8.dp)
                Text(
                    "${state.value.profile.firstname} ${state.value.profile.lastname}",
                    style = MatuleMeTheme.typography.titleProfile,
                    modifier = Modifier.align(Alignment.CenterHorizontally))
                SpacerHeight(8.dp)
                Text(
                    "Изменить фото профиля",
                    style = MatuleMeTheme.typography.bestSeller.copy(fontSize = 12.sp),
                    modifier = Modifier.align(Alignment.CenterHorizontally))
                SpacerHeight(20.dp)
                EditProfileField("Имя", state.value.editProfile.firstname) {
                    vm.stateValue = state.value.copy(editProfile = state.value.editProfile.copy(firstname = it))
                }
                SpacerHeight(16.dp)
                EditProfileField("Фамилия", state.value.editProfile.lastname) {
                    vm.stateValue = state.value.copy(editProfile = state.value.editProfile.copy(lastname = it))
                }
                SpacerHeight(16.dp)
                EditProfileField("Адрес", state.value.editProfile.address) {
                    vm.stateValue = state.value.copy(editProfile = state.value.editProfile.copy(address = it))
                }
                SpacerHeight(16.dp)
                val regex = Regex("^(7|8)[0-9]{0,10}$")
                EditProfileField("Телефон", state.value.editProfile.phone) {
                    val filteredText = it.filter { item -> item.isDigit() }
                    if (regex.matches(filteredText) || filteredText.isEmpty()) {
                        vm.stateValue = state.value.copy(editProfile = state.value.editProfile.copy(phone = filteredText))
                    }
                }
            }
            ProfileStates.Card -> {

            }
        }

    }

}

