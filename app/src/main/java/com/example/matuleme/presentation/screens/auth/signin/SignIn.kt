package com.example.matuleme.presentation.screens.auth.signin

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.matuleme.presentation.components.buttons.ButtonBack
import com.example.matuleme.presentation.components.buttons.ButtonMaxWidth
import com.example.matuleme.presentation.components.dialogs.DialogError
import com.example.matuleme.presentation.components.spacers.SpacerHeight
import com.example.matuleme.presentation.components.textfields.AuthTextFieldBase
import com.example.matuleme.presentation.components.textfields.AuthTextFieldPass
import com.example.matuleme.presentation.navigation.NavigationRoutes
import com.example.matuleme.presentation.ui.theme.MatuleMeTheme
import com.example.matuleme.presentation.ui.theme.subtextdark

@Composable
fun SignIn(controller: NavHostController, vm: SignInViewModel = hiltViewModel()) {

    val state = vm.state.collectAsState()
    val context = LocalContext.current

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
        ButtonBack {

        }
        SpacerHeight(12.dp)
        Text(
            text = "Привет!",
            modifier = Modifier.fillMaxWidth(),
            style = MatuleMeTheme.typography.authTitleScreen)
        SpacerHeight(8.dp)
        Text(
            text = "Заполните Свои Данные",
            modifier = Modifier.fillMaxWidth(),
            style = MatuleMeTheme.typography.authDescriptionScreen)
        SpacerHeight(35.dp)
        Text(
            text = "Email",
            style = MatuleMeTheme.typography.authTitleField)
        SpacerHeight(12.dp)
        AuthTextFieldBase(state.value.email, "xyz@gmail.com", "testEmail") {
            vm.stateValue = state.value.copy(email = it)
        }
        SpacerHeight(26.dp)
        Text(
            text = "Пароль",
            style = MatuleMeTheme.typography.authTitleField)
        SpacerHeight(12.dp)
        AuthTextFieldPass (state.value.password, "••••••••","testPass") {
            vm.stateValue = state.value.copy(password = it)
        }
        SpacerHeight(16.dp)
        Text(
            "Воcстановить",
            style = MatuleMeTheme.typography.authHintField,
            fontSize = 12.sp,
            modifier = Modifier.fillMaxWidth().clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                controller.navigate(NavigationRoutes.FORGOTPASSWORD)
            },
            color = subtextdark,
            textAlign = TextAlign.Right
        )
        SpacerHeight(24.dp)
        ButtonMaxWidth("Войти",
            state.value.email.isNotEmpty() && state.value.password.isNotEmpty()
        ) {
            vm.signIn(controller, context)
        }
        Spacer(modifier = Modifier.weight(1f))
        SpacerHeight(20.dp)
        Row(Modifier.fillMaxWidth(), Arrangement.Center) {
            Text(
                text = "Вы впервые?",
                style = MatuleMeTheme.typography.authHintField,
                fontSize = 16.sp,
                lineHeight = 16.38.sp
            )
            Spacer(Modifier.width(5.dp))
            Text(text = "Создать пользователя",
                style = MatuleMeTheme.typography.authTitleField,
                lineHeight = 16.38.sp,
                modifier = Modifier.clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    controller.navigate(NavigationRoutes.SIGNUP){
                        popUpTo(NavigationRoutes.SIGNIN) {
                            inclusive = true
                        }
                    }
                })
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    SignIn(rememberNavController())
}

