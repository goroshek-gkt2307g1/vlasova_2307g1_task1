package data.Dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class UserLoginRequestDto(
    @SerializedName("login")
    val login: String,
    @SerializedName("password")
    val password: String
)