package artem.kuptsov.observested.data

import java.io.Serializable
import java.sql.Date

data class UpdatePlaceForm (
    var name: String,
    var address: String,
    var working_hours_start: String,
    var working_hours_end: String,
) : Serializable