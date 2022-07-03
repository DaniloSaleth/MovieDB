package com.example.citmoviedatabase.networking.dataBase

import androidx.room.TypeConverter
import com.example.citmoviedatabase.model.Genre.Genre
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Converters {

    @TypeConverter // note this annotation
    fun fromIntList(optionValues: List<Int?>?): String? {
        if (optionValues == null) {
            return null
        }
        val gson = Gson()
        val type = object :
            TypeToken<List<Int?>?>() {}.type
        return gson.toJson(optionValues, type)
    }

    @TypeConverter
    fun toIntList(optionValuesString: String?): List<Int>? {
        if (optionValuesString == null) {
            return null
        }
        val gson = Gson()
        val type = object :
            TypeToken<List<Int?>?>() {}.type
        return gson.fromJson<List<Int>>(optionValuesString, type)
    }

    @TypeConverter
    fun fromGenreList(optionValues: List<Genre?>?): String? {
        if (optionValues == null) {
            return null
        }
        val gson = Gson()
        val type = object :
            TypeToken<List<Genre?>?>() {}.type
        return gson.toJson(optionValues, type)
    }

    @TypeConverter
    fun toGenreList(optionValuesString: String?): List<Genre>? {
        if (optionValuesString == null) {
            return null
        }
        val gson = Gson()
        val type = object :
            TypeToken<List<Genre?>?>() {}.type
        return gson.fromJson<List<Genre>>(optionValuesString, type)
    }

}