package viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.AuthRepository
import data.Dto.GroupDto
import data.Dto.RegistrationRequestDto
import data.Dto.UserDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel() : ViewModel() {

    private val repository = AuthRepository()
    private val _login = MutableStateFlow("") //login
    private val _password = MutableStateFlow("") //пароль
    private val _isLoader = MutableStateFlow(false) //показывать лоадер загрузки
    private val _errorMessage = MutableStateFlow<String?>(null) //ошибка
    private val _authAllowed = MutableStateFlow(false) //можно ли войти
    private val _users = MutableStateFlow<List<UserDto>>(emptyList())
    private val _groups = MutableStateFlow<List<GroupDto>>(emptyList())

    val login: StateFlow<String> = _login.asStateFlow()
    val password: StateFlow<String> = _password.asStateFlow()
    val isLoader: StateFlow<Boolean> = _isLoader.asStateFlow()
    val authAllowed: StateFlow<Boolean> = _authAllowed.asStateFlow()
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()
    val users: StateFlow<List<UserDto>> = _users.asStateFlow()
    val groups: StateFlow<List<GroupDto>> = _groups.asStateFlow()


    //обновить поле логина
    fun updateLogin(value: String) {
        _login.value = value
        _errorMessage.value = null
    }

    //обновить поле пароля
    fun updatePassword(value: String) {
        _password.value = value
        _errorMessage.value = null
    }

    fun loadUsers()
    {
        _errorMessage.value = null
        viewModelScope.launch {
            try {
                val users = repository.getUsers()
                _users.value = users
            }
            catch(e: Exception){
                _errorMessage.value = "Ошибка загрузки пользователей: ${e.message}"
            }
        }
    }

    fun loadGroups()
    {
        _errorMessage.value = null
        viewModelScope.launch {
            try {
                val groups = repository.getGroups()
                _groups.value = groups
            }
            catch(e: Exception){
                _errorMessage.value = "Ошибка загрузки групп: ${e.message}"
            }
        }
    }

    //авторизоваться
    fun login()
    {
        _isLoader.value = true
        _errorMessage.value = null
        viewModelScope.launch {
            try {
                if ((_login.value.isNotEmpty() && _password.value.isNotEmpty())) {
                    val user = repository.login(
                        _login.value,
                        _password.value
                    )
                    _authAllowed.value = true
                }
            } catch (e: Exception) {
                _errorMessage.value = "Ошибка авторизации: ${e.message}"
            }
            finally {
            _isLoader.value = false
            }
        }
    }
    
    fun register(request: RegistrationRequestDto)
    {
        _isLoader.value = true
        _errorMessage.value = null
        
        viewModelScope.launch { 
            try {
                if ((request.login.isNotEmpty() &&
                        request.password.isNotEmpty() &&
                        request.email.isNotEmpty() &&
                        request.person.firstName.isNotEmpty()&&
                        request.person.lastName.isNotEmpty()))
                {
                    val user = repository.register(request                    )
                    _authAllowed.value = true
                    _isLoader.value = false

                }
                else {
                    _isLoader.value = false
                    _errorMessage.value = "Заполните все обязательные поля!"
                }

            }
            catch (e: Exception)
            {
                _isLoader.value = false
                _errorMessage.value = "Ошибка регистрации: ${e.message}"
            }
        }
    }


}