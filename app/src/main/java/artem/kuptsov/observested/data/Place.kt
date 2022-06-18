package artem.kuptsov.observested.data

import java.io.Serializable
import java.sql.Date

data class Place (
    var id: Int,
    var name: String,
    var address: String,
    var longitude: Double,
    var latitude: Double,
    var working_hours_start: String,
    var working_hours_end: String,
    var created_at: Date,
    var updated_at: Date,
) : Serializable