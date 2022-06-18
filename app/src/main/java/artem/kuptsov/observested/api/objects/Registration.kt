package artem.kuptsov.observested.api.objects

import artem.kuptsov.observested.api.RetrofitClient

object Registration {
    private val REGISTRATION_URL = "https://shelter-service.herokuapp.com/registration/"

    val registration: Registration
        get () = RetrofitClient.getClient(REGISTRATION_URL).create(Registration::class.java)
}