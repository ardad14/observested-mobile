package artem.kuptsov.observested.api.objects

import artem.kuptsov.observested.api.RetrofitClient
import artem.kuptsov.observested.api.interfaces.EditPlace

object EditPlace {
    private val GET_URL = "http://192.168.1.6/api/place/{id}"

    val retrofitService: EditPlace
        get() = RetrofitClient.getClient(GET_URL).create(EditPlace::class.java)
}