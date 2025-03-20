package com.example.matuleme.presentation.screens.auth.forgotpassword

import android.annotation.SuppressLint
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.matuleme.presentation.components.buttons.ButtonBack
import com.example.matuleme.presentation.components.buttons.ButtonMaxWidth
import com.example.matuleme.presentation.components.dialogs.DialogCheckEmail
import com.example.matuleme.presentation.components.dialogs.DialogError
import com.example.matuleme.presentation.components.spacers.SpacerHeight
import com.example.matuleme.presentation.components.textfields.AuthTextFieldBase
import com.example.matuleme.presentation.navigation.NavigationRoutes
import com.example.matuleme.presentation.ui.theme.MatuleMeTheme

@SuppressLint("RestrictedApi")
@Composable
fun ForgotPassword(controller: NavHostController, vm: ForgotPasswordViewModel = hiltViewModel()) {

    val state = vm.state.collectAsState()

    if(state.value.dialogErrorIsOpen) {
        DialogError("Ошибка", state.value.error) {
            vm.stateValue = state.value.copy(dialogErrorIsOpen = false)
        }
    }

    if(state.value.dialogSendCodeIsOpen) {
        DialogCheckEmail {
            vm.stateValue = state.value.copy(dialogSendCodeIsOpen = false)
            controller.navigate(NavigationRoutes.OTP + "/${state.value.email}")
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
        SpacerHeight(12.dp)
        Text(
            text = "Забыл Пароль",
            modifier = Modifier.fillMaxWidth(),
            style = MatuleMeTheme.typography.authTitleScreen)
        SpacerHeight(8.dp)
        Text(
            text = "Введите Свою Учетную Запись\n" +
                    "Для Сброса",
            modifier = Modifier.fillMaxWidth(),
            style = MatuleMeTheme.typography.authDescriptionScreen)
        SpacerHeight(40.dp)
        AuthTextFieldBase(state.value.email, "xyz@gmail.com", "testEmail") {
            vm.stateValue = state.value.copy(email = it)
        }
        SpacerHeight(40.dp)
        ButtonMaxWidth("Отправить", state.value.email.isNotEmpty()) {
            vm.sendCode()
        }
        Spacer(modifier = Modifier.weight(1f))
    }

}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    ForgotPassword(rememberNavController())
}

