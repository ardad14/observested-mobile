package artem.kuptsov.observested.api.interfaces

import artem.kuptsov.observested.data.Place
import artem.kuptsov.observested.ui.MainActivity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface GetPlacesForUser {
    @GET("http://192.168.1.6/api/place/places/")
    fun getPlacesForUser(@Header("Authorization") authToken: String): Call<ArrayList<Place>>
}