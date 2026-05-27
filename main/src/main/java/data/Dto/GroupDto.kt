package data.Dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class GroupDto(
    @SerializedName("groupId")
    val id: Int,
    @SerializedName("groupName")
    val name: String
)