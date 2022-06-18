package artem.kuptsov.observested.data.user

import java.io.Serializable

data class UserLoginResponse (
    var userId: Int,
    var role: String,
    var token: String,
) : Serializable