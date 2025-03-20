package com.example.matuleme.presentation.screens.main.home.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.matuleme.domain.models.Category
import com.example.matuleme.presentation.ui.theme.accent
import com.example.matuleme.presentation.ui.theme.background
import com.example.matuleme.presentation.ui.theme.block
import com.example.matuleme.presentation.ui.theme.raleway
import com.example.matuleme.presentation.ui.theme.text

@Composable
fun CategoryItem(item: Category, isSelected: Boolean, onClick: (Category) -> Unit) {
    Button(
        onClick = { onClick(item) },
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = block,
            containerColor = if (!isSelected) block else accent
        ), modifier = Modifier
            .height(40.dp)
            .width(100.dp)
    ) {
        Text(
            item.title,
            fontSize = 12.sp,
            color = if (!isSelected) text else background,
            fontFamily = raleway,
            fontWeight = FontWeight.Normal
        )
    }
}