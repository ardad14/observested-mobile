package artem.kuptsov.observested.data

import java.io.Serializable
import java.sql.Date

data class UpdatePlaceResponse (
    var updated: Boolean,
) : Serializable