package com.example.matuleme.presentation.screens.main.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.matuleme.R
import com.example.matuleme.domain.models.Product
import com.example.matuleme.presentation.components.spacers.SpacerHeight
import com.example.matuleme.presentation.ui.theme.MatuleMeTheme
import com.example.matuleme.presentation.ui.theme.accent
import com.example.matuleme.presentation.ui.theme.background
import com.example.matuleme.presentation.ui.theme.block
import com.example.matuleme.presentation.ui.theme.raleway
import com.example.matuleme.presentation.ui.theme.red
import com.example.matuleme.presentation.ui.theme.text

@Composable
fun RowScope.ProductItem(product: Product, isFavourite: Boolean, photo: String, onClickFav: () -> Unit) {
    var height by remember { mutableStateOf(0.dp) }
    val density = LocalDensity.current
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(block)
            .padding(top = 9.dp, start = 9.dp)
            .heightIn(min = height)
            .onSizeChanged { height = with(density) { it.height.toDp() } }
    ) {
        var favIconColor: Color = text
        var favIconRes = R.drawable.icon_no_fav
        if (isFavourite) {
            favIconColor = red
            favIconRes = R.drawable.icon_yes_fav
        }
        //иконка сердца
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(background),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(favIconRes),
                contentDescription = "",
                tint = favIconColor,
                modifier = Modifier.padding(8.dp).size(12.dp).clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    onClickFav()
                }
            )
        }
        Column(modifier = Modifier.fillMaxHeight(1f).padding(end = 9.dp), verticalArrangement = Arrangement.Bottom) {
            //картинка
            val imgState = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(photo)
                    .size(Size.ORIGINAL).build()
            ).state
            if (imgState is AsyncImagePainter.State.Error) {
                CircularProgressIndicator()
            }
            if (imgState is AsyncImagePainter.State.Success) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .offset(y = -20.dp)
                        .height(100.dp)
                        .clip(RoundedCornerShape(15.dp)),
                    painter = imgState.painter,
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
            }
            //текст
            Text("BEST SELLER", style = MatuleMeTheme.typography.bestSeller)
            SpacerHeight(8.dp)
            Text(product.title, style = MatuleMeTheme.typography.authHintField, maxLines = 1, overflow = TextOverflow.Visible)
            SpacerHeight(14.dp)
            Spacer(modifier = Modifier.weight(1f))
            Text("₽${String.format("%.2f", product.cost)}")
            SpacerHeight(8.dp)
        }
        //кнопка корзины (не кликается
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 16.dp))
                .background(accent)
                .align(Alignment.BottomEnd)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.icon_plus),
                contentDescription = "",
                tint = block,
                modifier = Modifier.padding(8.dp).size(20.dp).clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    onClickFav()
                }
            )
        }
    }
}