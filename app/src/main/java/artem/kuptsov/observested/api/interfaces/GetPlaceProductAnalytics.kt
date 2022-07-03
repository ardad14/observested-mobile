package artem.kuptsov.observested.api.interfaces

import artem.kuptsov.observested.data.Place
import artem.kuptsov.observested.data.Product
import artem.kuptsov.observested.ui.MainActivity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface GetPlaceProductAnalytics {
    @GET("http://192.168.1.6/api/analytics/products/{id}")
    fun getPlaceProductAnalytics(@Header("Authorization") authToken: String, @Path("id") placeId: Int): Call<ArrayList<Product>>
}