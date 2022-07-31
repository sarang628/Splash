package com.sarang.screen_splash

import android.content.Context
import androidx.fragment.app.FragmentManager
import com.example.torang_core.navigation.SplashNavigation

class SplashModule {
    fun provideSplashNavigation(): SplashNavigation {
        return object : SplashNavigation {
            override fun registerDeviceFingerPrint(onRegisteredFingerPringListener: SplashNavigation.OnRegisteredFingerPringListener?) {

            }

            override val isLogin: Boolean = false


            override fun goLogin(fragmentManager: FragmentManager?) {
                TODO("not yet")
            }

            override fun goLogin(context: Context) {
                TODO("Not yet implemented")
            }

            override fun goMain(fragmentManager: FragmentManager?) {
                TODO("not yet")
            }

            override fun goMain(context: Context) {
                TODO("Not yet implemented")
            }
        }
    }
}