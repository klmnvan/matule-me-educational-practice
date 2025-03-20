package com.example.matuleme.domain.repository

import android.content.Context
import android.content.SharedPreferences
import com.example.matuleme.presentation.screens.splash.components.Acts

object CacheRepository {
    private lateinit var actSystem: SharedPreferences

    fun init(context: Context) {
        actSystem = context.getSharedPreferences("actSystem", Context.MODE_PRIVATE)
    }

    var act: Int
        get() = actSystem.getInt("act", Acts.SIGNIN)
        set(value) = actSystem.edit().putInt("act", value).apply()

    var uuidCurrentUser: String
        get() = actSystem.getString("current user uuid", "")!!
        set(value) = actSystem.edit().putString("current user uuid", value).apply()
}