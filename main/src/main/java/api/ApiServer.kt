package api

import data.Dto.AuthResponseDto
import data.Dto.GroupDto
import data.Dto.RegistrationRequestDto
import data.Dto.UserDto
import data.Dto.UserInputDto
import data.Dto.UserLoginRequestDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface ApiServer {

    //1 пользователь
    @GET("users/{id}")
    suspend fun getUser(id: Long): UserDto

    @PUT("users/{id}")
    suspend fun updateUser(@Body userInputDto: UserInputDto): UserDto

    @DELETE("users/{id}")
    suspend fun deleteUser(id: Long)

    @GET("users")
    suspend fun listUsers(): List<UserDto>

    @GET("users/login/{login}")
    suspend fun getLogin(login: String): UserDto

    @GET("users/email/{email}")
    suspend fun getEmail(email: String): UserDto

    @GET("groups")
    suspend fun listGroups(): List<GroupDto>

    @POST("auth/register")
    suspend fun register(@Body registrationRequestDto: RegistrationRequestDto): AuthResponseDto

    @POST("auth/login")
    suspend fun login(@Body loginRequestDto: UserLoginRequestDto): AuthResponseDto
}
