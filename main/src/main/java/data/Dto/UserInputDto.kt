package data.Dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


@Serializable
data class UserInputDto(
    @SerializedName("login")
    val login: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("phoneNumber")
    val phoneNumber: String?,
    @SerializedName("roleId")
    val roleId: Long,
    @SerializedName("authAllowed")
    val authAllowed: Boolean,
    @SerializedName("personId")
    val personId: Long
)