package data.Dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
import retrofit2.http.Field
import java.sql.Timestamp

@Serializable
data class ErrorDto (
    @SerializedName("description")
    val description: String,
    @SerializedName("time")
    val time: String,
    @SerializedName("fieldErrors")
    val fieldErrors: Map<String, String>
)