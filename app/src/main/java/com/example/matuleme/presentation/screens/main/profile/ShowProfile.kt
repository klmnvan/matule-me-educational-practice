package com.example.matuleme.presentation.screens.main.profile

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
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
import com.example.matuleme.presentation.ui.theme.background
import com.example.matuleme.presentation.ui.theme.block
import com.example.matuleme.presentation.ui.theme.raleway
import com.example.matuleme.presentation.ui.theme.text
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import com.google.zxing.oned.Code128Writer
import com.google.zxing.qrcode.QRCodeWriter
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel

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
            .verticalScroll(rememberScrollState())
            .background(block)
            .padding(horizontal = 20.dp)
            .padding(top = 60.dp, bottom = 120.dp)
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
                Box(modifier = Modifier.clickable {
                    vm.stateValue = state.value.copy(stateScreen = ProfileStates.Card)
                }) {
                    if (state.value.profile.id != "")
                        BarcodeGenerator(state.value.profile.id)
                }
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
                Box(modifier = Modifier.fillMaxWidth()) {
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(background)
                            .size(44.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.icon_arrow),
                            contentDescription = "",
                            tint = text,
                            modifier = Modifier.clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ) {
                                vm.stateValue = state.value.copy(stateScreen = ProfileStates.Show)
                            }
                        )
                    }
                    SpacerWidth(12.dp)
                    Text("Карта лояльности",
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
                SpacerHeight(20.dp)
                Column (modifier = Modifier.background(block).fillMaxHeight(0.5f)) {
                    if (state.value.profile.id != "") BarcodeGeneratorFullScreen(state.value.profile.id)
                }
            }
        }

    }

}

fun generateBarcodeBitmap(text: String, width: Int, height: Int): Bitmap? {
    val writer = Code128Writer() // Используем Code128Writer для CODE_128
    return try {
        val hints = mutableMapOf<EncodeHintType, Any>()
        hints[EncodeHintType.MARGIN] = 1 // Устанавливаем отступы

        val bitMatrix: BitMatrix = writer.encode(text, BarcodeFormat.CODE_128, width, height, hints)
        val barcodeWidth = bitMatrix.width
        val barcodeHeight = bitMatrix.height
        val barcodeBitmap = Bitmap.createBitmap(barcodeWidth, barcodeHeight, Bitmap.Config.RGB_565)

        for (x in 0 until barcodeWidth) {
            for (y in 0 until barcodeHeight) {
                barcodeBitmap.setPixel(x, y, if (bitMatrix[x, y]) android.graphics.Color.BLACK else android.graphics.Color.WHITE)
            }
        }

        barcodeBitmap
    } catch (e: WriterException) {
        e.printStackTrace()
        null
    }
}

@Composable
fun BarcodeGenerator(text: String) {
    var barcodeBitmap by remember { mutableStateOf<Bitmap?>(null) }

    val context = LocalContext.current
    val displayMetrics = context.resources.displayMetrics
    val screenWidth = displayMetrics.widthPixels
    val barcodeHeight = 100.dp
    LaunchedEffect(text) {
        barcodeBitmap = generateBarcodeBitmap(text, screenWidth, barcodeHeight.value.toInt())
    }

    barcodeBitmap?.let { bitmap ->
        Image(
            bitmap = bitmap.asImageBitmap(),
            contentDescription = "Barcode",
            modifier = Modifier
                .fillMaxWidth() // Занимает всю ширину экрана
                .height(barcodeHeight) // Фиксированная высота
        )
    }
}

@Composable
fun BarcodeGeneratorFullScreen(text: String) {
    var barcodeBitmap by remember { mutableStateOf<Bitmap?>(null) }

    val context = LocalContext.current
    val displayMetrics = context.resources.displayMetrics
    val screenWidth = (displayMetrics.widthPixels * 0.9).toInt()
    val screenHeight = (displayMetrics.heightPixels * 0.6).toInt()

    LaunchedEffect(text) {
        barcodeBitmap = generateBarcodeBitmap(text, screenWidth, screenHeight)
    }

    barcodeBitmap?.let { bitmap ->
        Image(
            bitmap = bitmap.asImageBitmap(),
            contentDescription = "Barcode",
            modifier = Modifier
                .fillMaxSize() // Занимает всю ширину и высоту экрана
        )
    }
}

