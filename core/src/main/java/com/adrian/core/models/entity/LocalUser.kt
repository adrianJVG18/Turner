package com.adrian.core.models.entity

import com.google.gson.Gson

data class LocalUser(
    val email: String? = "",
    val pass: String? = ""
) {

    override fun toString(): String {
        return "email:$email, pass:$pass"
    }

    fun toJson(): String {
        return gson.toJson(this)
    }

    companion object {
        private val gson = Gson()

        fun fromJson(json: String): LocalUser {
            return gson.fromJson(json, LocalUser::class.java)
        }
    }
}