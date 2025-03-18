package com.example.matuleme.presentation.screens.auth.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.matuleme.presentation.components.buttons.ButtonBack
import com.example.matuleme.presentation.components.buttons.ButtonMaxWidth
import com.example.matuleme.presentation.components.spacers.SpacerHeight
import com.example.matuleme.presentation.components.textfields.AuthTextFieldBase
import com.example.matuleme.presentation.components.textfields.AuthTextFieldPass
import com.example.matuleme.presentation.ui.theme.MatuleMeTheme

@Composable
fun SignUp(controller: NavHostController, vm: SignUpViewModel = hiltViewModel()) {

    val state = vm.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(MatuleMeTheme.colors.container)
            .padding(horizontal = 20.dp, vertical = 60.dp)
    ) {
        ButtonBack {

        }
        SpacerHeight(12.dp)
        Text(
            text = "Регистрация",
            modifier = Modifier.fillMaxWidth(),
            style = MatuleMeTheme.typography.authTitleScreen)
        SpacerHeight(8.dp)
        Text(
            text = "Заполните Свои Данные",
            modifier = Modifier.fillMaxWidth(),
            style = MatuleMeTheme.typography.authDescriptionScreen)
        SpacerHeight(54.dp)
        Text(
            text = "Ваше имя",
            style = MatuleMeTheme.typography.authTitleField)
        SpacerHeight(12.dp)
        AuthTextFieldBase(state.value.email, "xxxxxxxx", "testName") {
            vm.stateValue = state.value.copy(email = it)
        }
        SpacerHeight(12.dp)
        Text(
            text = "Email",
            style = MatuleMeTheme.typography.authTitleField)
        SpacerHeight(12.dp)
        AuthTextFieldBase(state.value.email, "xyz@gmail.com", "testEmail") {
            vm.stateValue = state.value.copy(email = it)
        }
        SpacerHeight(12.dp)
        Text(
            text = "Пароль",
            style = MatuleMeTheme.typography.authTitleField)
        SpacerHeight(12.dp)
        AuthTextFieldPass (state.value.password, "••••••••","testPass") {
            vm.stateValue = state.value.copy(password = it)
        }
        SpacerHeight(12.dp)
        //чекбокс будет
        SpacerHeight(24.dp)
        ButtonMaxWidth("Зарегистрироваться") {
            //vm.signIn(controller, context)
        }
        Spacer(modifier = Modifier.weight(1f))
        SpacerHeight(20.dp)
        Row(Modifier.fillMaxWidth(), Arrangement.Center) {
            Text(
                text = "Есть аккаунт?",
                style = MatuleMeTheme.typography.authHintField,
                fontSize = 16.sp,
                lineHeight = 16.38.sp)
            Spacer(Modifier.width(5.dp))
            Text(text = "Войти",
                style = MatuleMeTheme.typography.authTitleField,
                lineHeight = 16.38.sp,
                modifier = Modifier.clickable {

                })
        }

    }

}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    SignUp(rememberNavController())
}
