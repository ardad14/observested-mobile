package artem.kuptsov.observested.data

import java.io.Serializable
import java.sql.Time

data class Customer (
    var id: Int?,
    var name: String,
    var surname: String,
    var age: String,
) : Serializable