package artem.kuptsov.observested.api.interfaces

import artem.kuptsov.observested.data.user.UserLoginForm
import artem.kuptsov.observested.data.user.UserLoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface Login {
    @POST("http://192.168.1.6/api/login/")
    fun login(@Body requestBody: UserLoginForm): Call<UserLoginResponse>;
}