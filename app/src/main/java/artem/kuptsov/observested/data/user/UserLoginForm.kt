package artem.kuptsov.observested.data.user

import java.io.Serializable

data class UserLoginForm (
    var email: String,
    var password: String
) : Serializable