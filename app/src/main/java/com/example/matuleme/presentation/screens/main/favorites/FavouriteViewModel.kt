package com.example.matuleme.presentation.screens.main.favorites

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.matuleme.data.states.FavouriteSt
import com.example.matuleme.data.states.HomeSt
import com.example.matuleme.domain.models.Category
import com.example.matuleme.domain.models.Product
import com.example.matuleme.domain.network.Constants.supabase
import com.example.matuleme.domain.network.Requests
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.storage.storage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(): ViewModel() {

    private val _state = MutableStateFlow(FavouriteSt())
    val state: StateFlow<FavouriteSt> = _state.asStateFlow()

    var stateValue: FavouriteSt
        get() = _state.value
        set(value) {
            _state.value = value
        }

    @SuppressLint("SuspiciousIndentation")
    fun getData() {
        viewModelScope.launch {
            try {
                val listProduct = Requests.getAllProducts().toMutableList()
                val listIdFavProduct = Requests.getIdFavProducts().toMutableList()
                val bucket = supabase.storage.from("products")
                val files = bucket.list().toMutableList()
                val listUrl: MutableList<String> = mutableListOf()
                files.forEach { name ->
                    listUrl.add(bucket.publicUrl(name.name))
                }
                stateValue = stateValue.copy(
                    products = listProduct,
                    idFavSneakers = listIdFavProduct,
                    listBucket = listUrl
                )
            } catch (e: Exception) {
                stateValue = stateValue.copy(dialogIsOpen = true, error = e.message.toString())
                Log.d("getData() | ошибка", e.message.toString())
            }
        }
    }

    fun clickFavIcon(product: Product) {
        if(stateValue.idFavSneakers.contains(product.id)) deleteFav(product.id)
        else addFav(product.id)
    }

    fun deleteFav(productId: String) {
        viewModelScope.launch {
            try {
                Requests.deleteFavItem(productId)
                getData()
            } catch (e: Exception) {
                stateValue = stateValue.copy(dialogIsOpen = true, error = e.message.toString())
                Log.d("удаление из избранного | ошибка", e.message.toString())
            }
        }
    }

    fun addFav(productId: String) {
        viewModelScope.launch {
            try {
                Requests.addProductInFav(productId)
                getData()
            } catch (e: Exception) {
                stateValue = stateValue.copy(dialogIsOpen = true, error = e.message.toString())
                Log.d("добавление в избранное | ошибка", e.message.toString())
            }
        }
    }

}