package com.example.matuleme.presentation.components.checkboxes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.matuleme.R
import com.example.matuleme.presentation.components.spacers.SpacerWidth
import com.example.matuleme.presentation.ui.theme.MatuleMeTheme
import com.example.matuleme.presentation.ui.theme.background
import com.example.matuleme.presentation.ui.theme.block
import com.example.matuleme.presentation.ui.theme.red
import com.example.matuleme.presentation.ui.theme.text

@Composable
fun CheckboxSignUp(value: Boolean, onValueChange: (Boolean) -> Unit) {

    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier.clip(CircleShape).background(background).padding(6.dp), contentAlignment = Alignment.Center) {
            Icon(imageVector = ImageVector.vectorResource(R.drawable.icon_shield), contentDescription = "",
                modifier = Modifier.size(8.dp), tint = text
            )
        }
        SpacerWidth(12.dp)
        Text(text = "Даю согласие на обработку персональных данных", style = MatuleMeTheme.typography.textCheckbox)
    }

}