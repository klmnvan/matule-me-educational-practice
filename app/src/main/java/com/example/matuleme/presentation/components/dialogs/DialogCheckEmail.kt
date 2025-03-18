package com.example.matuleme.presentation.components.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.matuleme.R
import com.example.matuleme.presentation.components.spacers.SpacerHeight
import com.example.matuleme.presentation.ui.theme.MatuleMeTheme
import com.example.matuleme.presentation.ui.theme.accent
import com.example.matuleme.presentation.ui.theme.block

@Composable
fun DialogCheckEmail(onDismissRequest: () -> Unit) {
    Dialog(onDismissRequest = {
        onDismissRequest()
    }) {
        Column(
            modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp)).background(block).padding(vertical = 30.dp, horizontal = 10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.clip(CircleShape).background(accent).padding(10.dp), contentAlignment = Alignment.Center) {
                Icon(imageVector = ImageVector.vectorResource(R.drawable.icon_email), contentDescription = "",
                    modifier = Modifier.size(24.dp), tint = block)
            }
            SpacerHeight(24.dp)
            Text("Проверьте Ваш Email", style = MatuleMeTheme.typography.titleDialog)
            SpacerHeight(8.dp)
            Text(
                "Мы Отправили Код Восстановления Пароля На Вашу Электронную Почту.",
                style = MatuleMeTheme.typography.descDialog
            )
        }
    }
}