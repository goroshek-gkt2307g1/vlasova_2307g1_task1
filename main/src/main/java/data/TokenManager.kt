package data

import android.content.Context
import android.content.SharedPreferences


object TokenManager {

    val token: String?
        get() = sharedPreferences.getString(jwt_token, null)

    private lateinit var sharedPreferences : SharedPreferences
    private const val jwt_token = "jwt_token"

    fun init(context: Context)
    {
        sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    }

    fun saveToken(token: String?)
    {
        val editor = sharedPreferences.edit()
        editor.putString(jwt_token, token)
        editor.apply()
    }

    fun clearToken()
    {
        val editor = sharedPreferences.edit()
        editor.remove(jwt_token)
        editor.apply()
    }


}