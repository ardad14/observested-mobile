package artem.kuptsov.observested.data

import java.io.Serializable
import java.sql.Date

data class CustomerPlace (
    var place_id: Int,
    var customer_id: Int,
    var spend_money: Int,
    var created_at: Date
) : Serializable