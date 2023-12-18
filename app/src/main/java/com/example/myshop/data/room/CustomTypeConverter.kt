package com.example.myshop.data.room

import androidx.room.TypeConverter
import com.example.myshop.data.dataClass.Rating
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CustomTypeConverter {

    @TypeConverter
    fun fromCustomType(customType: Rating): String {
        return Gson().toJson(customType)
    }

    @TypeConverter
    fun toCustomType(customTypeString: String): Rating {
        val type = object : TypeToken<Rating>() {}.type
        return Gson().fromJson(customTypeString, type)
    }
}