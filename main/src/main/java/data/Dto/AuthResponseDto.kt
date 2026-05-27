package data.Dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class AuthResponseDto(
    @SerializedName("token")
    val token: String
)