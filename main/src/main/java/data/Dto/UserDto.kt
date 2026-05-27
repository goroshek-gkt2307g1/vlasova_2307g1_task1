package data.Dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class UserDto (
    @SerializedName("userId")
    val id: Int,
    @SerializedName("login")
    val login: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("phoneNumber")
    val phoneNumber: String?,
    @SerializedName("roleId")
    val roleId: Int,
    @SerializedName("authAllowed")
    val authAllowed: Boolean,
    @SerializedName("personId")
    val personId: Long,
    @SerializedName("createdDate")
    val createdDate: String,
    @SerializedName("lastLoginDate")
    val lastLoginDate: String?

)