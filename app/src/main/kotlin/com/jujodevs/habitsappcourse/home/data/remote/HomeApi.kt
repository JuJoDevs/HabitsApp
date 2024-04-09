package com.jujodevs.habitsappcourse.home.data.remote

import com.jujodevs.habitsappcourse.home.data.remote.dto.HabitResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Query

interface HomeApi {
    companion object {
        const val BASE_URL =
            "https://dailyhabits-f9d22-default-rtdb.europe-west1.firebasedatabase.app/"
    }

    @GET("habits.json")
    suspend fun getAllHabits(@Query("auth") token: String?): HabitResponse

    @PATCH("habits.json")
    suspend fun insertHabit(@Query("auth") token: String?, @Body habitResponse: HabitResponse)
}