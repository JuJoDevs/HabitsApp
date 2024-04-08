package com.jujodevs.habitsappcourse.home.data.local.typeconverter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.reflect.ParameterizedType
import javax.inject.Inject

@ProvidedTypeConverter
class HomeTypeConverter @Inject constructor(
    private val moshi: Moshi,
) {
    @TypeConverter
    fun fromFrequency(days: List<Int>): String {
        return MoshiAdapters.ListInt(moshi).adapter.toJson(days)
    }

    @TypeConverter
    fun toFrequency(value: String): List<Int> {
        return MoshiAdapters.ListInt(moshi).adapter.fromJson(value) ?: emptyList()
    }

    @TypeConverter
    fun fromCompletedDates(days: List<Long>): String {
        return MoshiAdapters.ListLong(moshi).adapter.toJson(days)
    }

    @TypeConverter
    fun toCompletedDates(value: String): List<Long> {
        return MoshiAdapters.ListLong(moshi).adapter.fromJson(value) ?: emptyList()
    }

    sealed class MoshiAdapters<T>(
        type: ParameterizedType,
        moshi: Moshi,
    ) {
        data class ListInt(val moshi: Moshi) : MoshiAdapters<List<Int>>(
            Types.newParameterizedType(List::class.java, Int::class.javaObjectType),
            moshi
        )

        data class ListLong(val moshi: Moshi) : MoshiAdapters<List<Long>>(
            Types.newParameterizedType(List::class.java, Long::class.javaObjectType),
            moshi
        )

        val adapter: JsonAdapter<T> = moshi.adapter(type)
    }
}