package artem.kuptsov.observested.data.user

import java.io.Serializable
import java.sql.Time

data class User (
    var id: Int?,
    var name: String,
    var surname: String,
    var email: String,
    var password: String,
    var role: String,
) : Serializable