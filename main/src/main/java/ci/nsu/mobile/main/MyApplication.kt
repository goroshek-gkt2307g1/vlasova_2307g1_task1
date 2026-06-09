package ci.nsu.mobile.main

import android.app.Application
import androidx.lifecycle.viewmodel.compose.viewModel
import data.TokenManager
import viewmodel.AuthViewModel

class MyApplication() : Application()
{
    override fun onCreate()
    {
        super.onCreate()
        TokenManager.init(this)
    }

}