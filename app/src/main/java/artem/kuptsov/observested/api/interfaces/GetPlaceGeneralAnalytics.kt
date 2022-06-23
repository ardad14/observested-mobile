package artem.kuptsov.observested.api.interfaces

import artem.kuptsov.observested.data.Place
import artem.kuptsov.observested.ui.MainActivity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface GetPlaceGeneralAnalytics {
    @GET("http://192.168.1.6/api/analytics/general/{id}")
    fun getPlaceGeneralAnalytics(@Header("Authorization") authToken: String, @Path("id") placeId: Int): Call<Place>
}