package ci.nsu.mobile.main

import android.app.Application
import data.TokenManager

class MyApplication() : Application()
{
    override fun onCreate()
    {
        super.onCreate()
        TokenManager.init(this)
    }
}