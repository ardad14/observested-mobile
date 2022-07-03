package artem.kuptsov.observested.api.interfaces

import artem.kuptsov.observested.data.Place
import artem.kuptsov.observested.data.UpdatePlaceForm
import artem.kuptsov.observested.data.UpdatePlaceResponse
import artem.kuptsov.observested.ui.MainActivity
import retrofit2.Call
import retrofit2.http.*

interface EditPlace {
    @PUT("http://192.168.1.6/api/place/{id}")
    fun updatePlace(
        @Header("Authorization") authToken: String,
        @Path("id") placeId: Int,
        @Body requestBody: UpdatePlaceForm
    ): Call<UpdatePlaceResponse>
}