package artem.kuptsov.observested.api.interfaces

import artem.kuptsov.observested.data.user.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface Registration {
    @POST("https://shelter-service.herokuapp.com/registration/")
    fun registration(@Body requestBody: User): Call<User>;
}