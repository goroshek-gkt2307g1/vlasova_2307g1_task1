package data.Dto

import android.text.format.DateUtils
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class PersonInputDto (
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("middleName")
    val middleName: String?,
    @SerializedName("birthDate")
    val birthDate: String?,
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("groupId")
    val groupId: Long
)