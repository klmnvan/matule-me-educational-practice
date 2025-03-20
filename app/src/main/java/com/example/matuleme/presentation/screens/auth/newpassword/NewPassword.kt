package com.example.matuleme.presentation.screens.auth.newpassword

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.matuleme.presentation.components.buttons.ButtonBack
import com.example.matuleme.presentation.components.buttons.ButtonMaxWidth
import com.example.matuleme.presentation.components.dialogs.DialogError
import com.example.matuleme.presentation.components.spacers.SpacerHeight
import com.example.matuleme.presentation.components.textfields.AuthTextFieldPass
import com.example.matuleme.presentation.ui.theme.MatuleMeTheme

@Composable
fun NewPassword(controller: NavHostController, email: String, vm: NewPasswordViewModel = hiltViewModel()) {

    val state = vm.state.collectAsState()

    if(state.value.dialogIsOpen) {
        DialogError("Ошибка", state.value.error) {
            vm.stateValue = state.value.copy(dialogIsOpen = false)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(MatuleMeTheme.colors.container)
            .padding(horizontal = 20.dp, vertical = 60.dp)
    ) {
        ButtonBack(controller)
        SpacerHeight(18.dp)
        Text(
            text = "Задать Новый Пароль",
            modifier = Modifier.fillMaxWidth(),
            style = MatuleMeTheme.typography.authTitleScreen)
        SpacerHeight(8.dp)
        Text(
            text = "Установите Новый Пароль Для Входа В Вашу Учетную Запись",
            modifier = Modifier.fillMaxWidth(),
            style = MatuleMeTheme.typography.authDescriptionScreen)
        SpacerHeight(35.dp)
        Text(
            text = "Пароль",
            style = MatuleMeTheme.typography.authTitleField)
        SpacerHeight(12.dp)
        AuthTextFieldPass(state.value.password, "••••••••", "testPass") {
            vm.stateValue = state.value.copy(password = it)
        }
        SpacerHeight(26.dp)
        Text(
            text = "Подтверждение пароля",
            style = MatuleMeTheme.typography.authTitleField)
        SpacerHeight(12.dp)
        AuthTextFieldPass (state.value.confirmPassword, "••••••••","testPassConfirm") {
            vm.stateValue = state.value.copy(confirmPassword = it)
        }
        SpacerHeight(40.dp)
        ButtonMaxWidth("Сохранить",
            state.value.password.isNotEmpty() && state.value.confirmPassword.isNotEmpty()
        ) {
            vm.updatePassword(controller)
        }
        Spacer(modifier = Modifier.weight(1f))
    }

}