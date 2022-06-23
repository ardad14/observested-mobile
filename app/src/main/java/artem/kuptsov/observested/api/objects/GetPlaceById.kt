package artem.kuptsov.observested.api.objects

import artem.kuptsov.observested.api.RetrofitClient
import artem.kuptsov.observested.api.interfaces.GetPlaceById

object GetPlaceById {
    private val GET_URL = "http://192.168.1.6/api/place/show/9"

    val retrofitService: GetPlaceById
        get() = RetrofitClient.getClient(GET_URL).create(GetPlaceById::class.java)
}