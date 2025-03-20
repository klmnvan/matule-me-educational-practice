package com.example.matuleme.presentation.screens.main.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.matuleme.R
import com.example.matuleme.presentation.components.dialogs.DialogError
import com.example.matuleme.presentation.components.spacers.SpacerHeight
import com.example.matuleme.presentation.screens.main.home.components.CategoryItem
import com.example.matuleme.presentation.screens.main.home.components.HomeStates
import com.example.matuleme.presentation.screens.main.home.components.ProductItem
import com.example.matuleme.presentation.ui.theme.MatuleMeTheme
import com.example.matuleme.presentation.ui.theme.background
import com.example.matuleme.presentation.ui.theme.block
import com.example.matuleme.presentation.ui.theme.red
import com.example.matuleme.presentation.ui.theme.text

@Composable
fun Favourite(controller: NavHostController, vm: FavouriteViewModel = hiltViewModel()) {

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
            .background(background)
            .padding(horizontal = 20.dp, vertical = 60.dp)
    ) {

        Box(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(block)
                    .size(44.dp)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.icon_arrow),
                    contentDescription = "",
                    tint = text,
                    modifier = Modifier
                        .align(Alignment.Center).clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            //vm.stateValue = state.value.copy(screenState = HomeStates.Main)
                        }
                )
            }
            Text("Избранное", style = MatuleMeTheme.typography.titleMainScreens,
                modifier = Modifier.align(Alignment.Center))
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(block)
                    .size(44.dp)
                    .align(Alignment.CenterEnd)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.icon_yes_fav),
                    contentDescription = "",
                    tint = red,
                    modifier = Modifier
                        .align(Alignment.Center).clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            //vm.stateValue = state.value.copy(screenState = HomeStates.Main)
                        }
                )
            }
        }

        SpacerHeight(20.dp)
        if(state.value.products.isNotEmpty()) {
            Row(modifier = Modifier) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(15.dp),
                    verticalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    var listProduct =
                        state.value.products.filter { state.value.idFavSneakers.contains(it.id) }
                    items(listProduct) { product ->
                        ProductItem(product, state.value.idFavSneakers.contains(product.id),
                            state.value.listBucket.first { it.contains(product.id) }) {
                            vm.clickFavIcon(product)
                        }
                    }
                }
            }
        }

    }

}

