package artem.kuptsov.observested.api.objects

import artem.kuptsov.observested.api.RetrofitClient
import artem.kuptsov.observested.api.interfaces.GetPlaceProductAnalytics

object GetPlaceProductAnalytics {
    private val GET_URL = "http://192.168.1.6/api/place/show/9"

    val retrofitService: GetPlaceProductAnalytics
        get() = RetrofitClient.getClient(GET_URL).create(GetPlaceProductAnalytics::class.java)
}