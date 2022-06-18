package artem.kuptsov.observested.api.objects

import android.content.Context
import artem.kuptsov.observested.api.RetrofitClient
import artem.kuptsov.observested.api.interfaces.GetPlacesForUser

object GetPlacesForUser {
    private val GET_URL = "http://192.168.1.6/api/place/places/"

    val retrofitService: GetPlacesForUser
        get() = RetrofitClient.getClient(GET_URL).create(GetPlacesForUser::class.java)
}