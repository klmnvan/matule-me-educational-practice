package com.example.matuleme.domain.network

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage

object Constants {
    val supabase = createSupabaseClient(
        supabaseUrl = "https://dxltgkczhwdvxcsceydk.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImR4bHRna2N6aHdkdnhjc2NleWRrIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDIyNDQ3NTUsImV4cCI6MjA1NzgyMDc1NX0.oav1hudoOXjozgUz3CqtC1lMdz3uEQsrn1729mW5tag"
    ) {
        install(Auth)
        install(Postgrest)
        install(Storage)
    }
}