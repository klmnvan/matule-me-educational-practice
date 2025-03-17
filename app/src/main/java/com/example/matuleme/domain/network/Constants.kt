package com.example.matuleme.domain.network

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest

object Constants {
    val supabase = createSupabaseClient(
        supabaseUrl = "https://zbxiqiazdhvdszvnuoyu.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InpieGlxaWF6ZGh2ZHN6dm51b3l1Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzQ0NDM5OTIsImV4cCI6MjA1MDAxOTk5Mn0.BjE3n1VIBzfZ02OzD9mjJlAOqKDIo4Rf0-G-uTcNcEI"
    ) {
        install(Auth)
        install(Postgrest)
    }
}