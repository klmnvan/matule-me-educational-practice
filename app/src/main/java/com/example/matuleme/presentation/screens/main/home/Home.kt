package com.example.matuleme.presentation.screens.main.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.matuleme.R
import com.example.matuleme.presentation.components.dialogs.DialogError
import com.example.matuleme.presentation.components.spacers.SpacerHeight
import com.example.matuleme.presentation.components.spacers.SpacerWidth
import com.example.matuleme.presentation.components.textfields.SearchTextField
import com.example.matuleme.presentation.navigation.NavigationRoutes
import com.example.matuleme.presentation.screens.main.home.components.CategoryItem
import com.example.matuleme.presentation.screens.main.home.components.HomeStates
import com.example.matuleme.presentation.screens.main.home.components.ProductItem
import com.example.matuleme.presentation.ui.theme.MatuleMeTheme
import com.example.matuleme.presentation.ui.theme.accent
import com.example.matuleme.presentation.ui.theme.background
import com.example.matuleme.presentation.ui.theme.block
import com.example.matuleme.presentation.ui.theme.raleway
import com.example.matuleme.presentation.ui.theme.text

@Composable
fun Home(controller: NavHostController, vm: HomeViewModel = hiltViewModel()) {

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
        when (state.value.screenState) {
            HomeStates.Main -> {

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
                    Text("Главная",
                        fontSize = 32.sp,
                        fontFamily = raleway,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.align(Alignment.Center))
                    SpacerWidth(12.dp)
                    Box (
                        modifier = Modifier.clip(CircleShape).background(block).align(Alignment.CenterEnd),
                    ) {
                        Icon(imageVector = ImageVector.vectorResource(id = R.drawable.icon_shop),
                            modifier = Modifier.padding(10.dp).size(25.dp),
                            contentDescription = "", tint = text
                        )
                    }
                }

                SpacerHeight(20.dp)

                Row(modifier = Modifier.fillMaxWidth()) {
                    SearchTextField(state.value.search) {
                        vm.stateValue = state.value.copy(search = it)
                    }
                    SpacerWidth(14.dp)
                    Box (
                        modifier = Modifier.clip(CircleShape).background(accent),
                    ) {
                        Icon(imageVector = ImageVector.vectorResource(id = R.drawable.icon_settings),
                            modifier = Modifier.padding(14.dp).size(25.dp),
                            contentDescription = "", tint = block
                        )
                    }
                }

                SpacerHeight(24.dp)

                Text(text = "Категории", style = MatuleMeTheme.typography.authTitleField)

                SpacerHeight(14.dp)

                LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    items(state.value.categories) { category ->
                        CategoryItem(category, false) {
                            vm.stateValue = state.value.copy(
                                selectedCategory = it,
                                screenState = HomeStates.Category
                            )
                        }
                    }

                }

                SpacerHeight(24.dp)

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Популярное", style = MatuleMeTheme.typography.authTitleField)
                    Spacer(modifier = Modifier.weight(1f))
                    Text(text = "Все", style = MatuleMeTheme.typography.authTitleField.copy(color = accent, fontSize = 12.sp))
                }

                SpacerHeight(30.dp)

                if(state.value.products.isNotEmpty()) {
                    Row(modifier = Modifier) {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            horizontalArrangement = Arrangement.spacedBy(15.dp),
                            verticalArrangement = Arrangement.spacedBy(15.dp)
                        ) {
                            items(state.value.products.take(2)) { product ->
                                ProductItem(product, state.value.idFavSneakers.contains(product.id),
                                    state.value.listBucket.first { it.contains(product.id) }) {
                                    vm.clickFavIcon(product)
                                }
                            }
                        }
                    }
                }

                SpacerHeight(30.dp)

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Акции", style = MatuleMeTheme.typography.authTitleField)
                    Spacer(modifier = Modifier.weight(1f))
                    Text(text = "Все", style = MatuleMeTheme.typography.authTitleField.copy(color = accent, fontSize = 12.sp))
                }

                SpacerHeight(20.dp)

                if(state.value.listBucket.isNotEmpty()) {
                    val imgState = rememberAsyncImagePainter(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(state.value.listBucket.first { it.contains("action.png") })
                            .size(Size.ORIGINAL).build()
                    ).state
                    if (imgState is AsyncImagePainter.State.Error) {
                        CircularProgressIndicator()
                    }
                    if (imgState is AsyncImagePainter.State.Success) {
                        Image(
                            modifier = Modifier
                                .fillMaxWidth(1f)
                                .height(100.dp)
                                .clip(RoundedCornerShape(15.dp)),
                            painter = imgState.painter,
                            contentDescription = "",
                            contentScale = ContentScale.FillWidth
                        )
                    }
                }
            }
            HomeStates.Category -> {

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
                                vm.stateValue = state.value.copy(screenState = HomeStates.Main)
                            }
                        )
                    }
                    Text(state.value.selectedCategory.title, style = MatuleMeTheme.typography.titleMainScreens,
                        modifier = Modifier.align(Alignment.Center))
                }

                SpacerHeight(24.dp)

                Text(text = "Категории", style = MatuleMeTheme.typography.authTitleField)

                SpacerHeight(24.dp)

                LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    items(state.value.categories) { category ->
                        var isSelectedItem = (category == state.value.selectedCategory)
                        if (!isSelectedItem) isSelectedItem = false
                        CategoryItem(category, isSelectedItem) {
                            vm.stateValue = state.value.copy(
                                selectedCategory = it
                            )
                        }
                    }
                }
                SpacerHeight(16.dp)
                if(state.value.products.isNotEmpty()) {
                    Row(modifier = Modifier) {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            horizontalArrangement = Arrangement.spacedBy(15.dp),
                            verticalArrangement = Arrangement.spacedBy(15.dp)
                        ) {
                            var listProduct =
                                state.value.products.filter { it.category_id == state.value.selectedCategory.id }
                            if (state.value.selectedCategory.title == "Все") listProduct = state.value.products
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

    }

}



