package com.adrian.core.models.converter

import com.google.gson.JsonElement

interface JsonConverter<T> {
    fun fromJson(json: JsonElement): T
    fun toJson(obj: T): String
}