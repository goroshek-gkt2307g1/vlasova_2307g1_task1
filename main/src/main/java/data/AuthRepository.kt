package data

import data.Dto.AuthResponseDto
import data.Dto.GroupDto
import data.Dto.RegistrationRequestDto
import data.Dto.UserDto
import data.Dto.UserLoginRequestDto

class AuthRepository(
)
{
    //залогиниться
    suspend fun login(login: String, password: String): AuthResponseDto
    {
        val response = RetrofitSingleton.serverApi.login(
            loginRequestDto = UserLoginRequestDto(login = login,
                password = password)
        )
        TokenManager.saveToken(response.token)
        return response
    }

    //зарегаться
    suspend fun register(registerRequest: RegistrationRequestDto): AuthResponseDto{
        val response = RetrofitSingleton.serverApi.register(
            registrationRequestDto = registerRequest
        )
        TokenManager.saveToken(response.token)
        return response
    }

    //получить пользователя
    suspend fun getUsers(): List<UserDto> {
        val response = RetrofitSingleton.serverApi.listUsers()
        return response
    }

    //получить группу пользователей
    suspend fun getGroups(): List<GroupDto> {
        val response = RetrofitSingleton.serverApi.listGroups()
        return response
    }



}