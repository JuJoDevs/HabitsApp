package com.jujodevs.habitsappcourse.home.api.data.local.typeconverter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import javax.inject.Inject

@ProvidedTypeConverter
class HomeTypeConverter @Inject constructor(
    private val moshi: Moshi,
) {
    private val intListAdapter: JsonAdapter<IntList> = moshi.adapter(IntList::class.java)
    private val longListAdapter: JsonAdapter<LongList> = moshi.adapter(LongList::class.java)


    @TypeConverter
    fun fromFrequency(days: List<Int>): String =
        intListAdapter.toJson(IntList(days))

    @TypeConverter
    fun toFrequency(value: String): List<Int> =
        intListAdapter.fromJson(value)?.list.orEmpty()

    @TypeConverter
    fun fromCompletedDates(days: List<Long>): String =
        longListAdapter.toJson(LongList(days))

    @TypeConverter
    fun toCompletedDates(value: String): List<Long> =
        longListAdapter.fromJson(value)?.list.orEmpty()
}

@JsonClass(generateAdapter = true)
data class IntList(val list: List<Int>)

@JsonClass(generateAdapter = true)
data class LongList(val list: List<Long>)
