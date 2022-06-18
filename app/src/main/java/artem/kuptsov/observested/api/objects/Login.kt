package artem.kuptsov.observested.api.objects

import artem.kuptsov.observested.api.RetrofitClient
import artem.kuptsov.observested.api.interfaces.Login

object Login {
    private val LOGIN_URL = "http://192.168.1.6/api/login/"

    val retrofitService: Login
        get () = RetrofitClient.getClient(LOGIN_URL).create(Login::class.java)
}