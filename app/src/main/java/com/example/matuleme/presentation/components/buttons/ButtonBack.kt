package com.example.matuleme.presentation.components.buttons

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.matuleme.R
import com.example.matuleme.presentation.ui.theme.background
import com.example.matuleme.presentation.ui.theme.text

@SuppressLint("RestrictedApi", "StateFlowValueCalledInComposition")
@Composable
fun ButtonBack(controller: NavHostController) {
    val lastScreenIsNotSplash = controller.currentBackStack.value.size > 2
    Box(
        modifier = Modifier.alpha(if (lastScreenIsNotSplash) 1f else 0f)
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
                if (lastScreenIsNotSplash) {
                    controller.navigateUp()
                }
            }
        )
    }
}