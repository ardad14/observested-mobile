package artem.kuptsov.observested.data

import java.io.Serializable
import java.sql.Time

data class Product (
    var id: Int?,
    var name: String,
    var purchase: Float,
    var price: Float,
    var available_amount: Int,
    var sold: Int,
) : Serializable