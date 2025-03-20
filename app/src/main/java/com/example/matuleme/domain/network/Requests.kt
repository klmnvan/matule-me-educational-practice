package com.example.matuleme.domain.network

import com.example.matuleme.domain.models.Category
import com.example.matuleme.domain.models.Favourite
import com.example.matuleme.domain.models.Product
import com.example.matuleme.domain.models.ProfileEnt
import com.example.matuleme.domain.repository.CacheRepository
import io.github.jan.supabase.postgrest.from
import java.util.UUID

object Requests {

    suspend fun getAllProducts(): List<Product> {
        return Constants.supabase.from("products").select().decodeList<Product>()
    }

    suspend fun getIdFavProducts(): List<String> {
        return Constants.supabase.from("favourite")
            .select {
                filter {
                    eq("user_id", CacheRepository.uuidCurrentUser)
                }
            }.decodeList<Favourite>().toList().map { it.product_id }
    }

    suspend fun getListCategory(): List<Category> {
        return Constants.supabase.from("categories").select().decodeList<Category>()
    }

    suspend fun addProductInFav(productId: String) {
        val favItem = Favourite(UUID.randomUUID().toString(), productId, CacheRepository.uuidCurrentUser)
        Constants.supabase.from("favourite").insert(favItem)
    }

    suspend fun deleteFavItem(productId: String) {
        Constants.supabase.from("favourite").delete {
            filter {
                eq("user_id", CacheRepository.uuidCurrentUser)
                eq("product_id", productId)
            }
        }
    }

    suspend fun getProfile(): ProfileEnt {
        return Constants.supabase.from("profiles")
            .select {
                filter {
                    eq("user_id", CacheRepository.uuidCurrentUser)
                }
            }.decodeSingle()
    }

}