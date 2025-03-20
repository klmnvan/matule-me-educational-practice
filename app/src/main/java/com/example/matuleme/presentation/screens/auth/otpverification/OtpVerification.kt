package com.example.matuleme.presentation.screens.auth.otpverification

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEachIndexed
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.matuleme.presentation.components.buttons.ButtonBack
import com.example.matuleme.presentation.components.dialogs.DialogError
import com.example.matuleme.presentation.components.spacers.SpacerHeight
import com.example.matuleme.presentation.screens.auth.otpverification.components.InputCell
import com.example.matuleme.presentation.screens.auth.otpverification.components.InputOtpRow
import com.example.matuleme.presentation.ui.theme.MatuleMeTheme

@Composable
fun OtpVerification(controller: NavHostController, email: String,  vm: OtpVerificationViewModel = hiltViewModel()) {

    val state = vm.state.collectAsState()
    val focusRequesters: List<FocusRequester> = List(6) { FocusRequester() }
    val code = remember { mutableStateListOf(*Array(6) { "" }) }

    LaunchedEffect(Unit) {
        vm.startTimer()
    }

    LaunchedEffect(state.value.code) {
        state.value.code.fastForEachIndexed { i, s ->
            code[i] = s
        }
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
            .background(MatuleMeTheme.colors.container)
            .padding(horizontal = 20.dp, vertical = 60.dp)
    ) {
        ButtonBack(controller)
        SpacerHeight(16.dp)
        Text(
            text = "OTP Проверка",
            modifier = Modifier.fillMaxWidth(),
            style = MatuleMeTheme.typography.authTitleScreen)
        SpacerHeight(8.dp)
        Text(
            text = "Пожалуйста, проверьте свою электронную почту, чтобы увидеть код подтверждения",
            modifier = Modifier.fillMaxWidth(),
            style = MatuleMeTheme.typography.authDescriptionScreen)
        SpacerHeight(16.dp)
        Text(text = "OTP Код", style = MatuleMeTheme.typography.authTitleField)
        SpacerHeight(20.dp)
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            for (i in 0 until 6) {
                InputCell(code[i], focusRequesters[i]) { newValue ->
                    if (newValue.length <= 1) {
                        code[i] = newValue
                        vm.stateValue = state.value.copy(code = code)
                        if (newValue.isNotEmpty() && i < 5) {
                            focusRequesters[i + 1].requestFocus()
                        }
                        else if (newValue.isEmpty() && i > 0) {
                            focusRequesters[i - 1].requestFocus()
                        }
                        if (newValue.isNotEmpty() && i == 5) {
                            focusRequesters[0].requestFocus()
                        }
                    }
                    if (state.value.code.all { it.isNotEmpty() }) {
                        vm.checkOtpCode(email, controller)
                    }
                }
            }
        }
        SpacerHeight(20.dp)
        if(state.value.timer == 0)
            Text(
                text = "Отправить заново",
                style = MatuleMeTheme.typography.clickableTextHint,
                modifier = Modifier.clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    vm.sendOtpAgain(email)
                }
            )
        else
            Text(
                text = "00:${state.value.timer}",
                style = MatuleMeTheme.typography.clickableTextHint,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Right
            )
        Spacer(modifier = Modifier.weight(1f))
    }

}



@Preview(showBackground = true)
@Composable
private fun Preview() {
    OtpVerification(rememberNavController(), "")
}