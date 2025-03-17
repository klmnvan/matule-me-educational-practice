package com.example.matuleme.domain.repository

import android.content.Context
import android.content.SharedPreferences

object CacheRepository {
    private lateinit var actSystem: SharedPreferences

    fun init(context: Context) {
        actSystem = context.getSharedPreferences("actSystem", Context.MODE_PRIVATE)
    }

    var act: Int
        get() = actSystem.getInt("act", 0)
        set(value) = actSystem.edit().putInt("act", value).apply()

    var uuidCurrentUser: String
        get() = actSystem.getString("current user uuid", "")!!
        set(value) = actSystem.edit().putString("current user uuid", value).apply()
}